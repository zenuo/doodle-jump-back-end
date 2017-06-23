package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.constant.TeamConstant;
import yz.doodlejump.entity.DTO.PlayerStatus;
import yz.doodlejump.entity.bean.Player;
import yz.doodlejump.entity.bean.Team;

import java.util.*;

/**
 * 队伍管理类
 */
public final class TeamManager {
    /**
     * 单例
     */
    private static final TeamManager INSTANCE = new TeamManager();

    /**
     * 日志记录对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamManager.class);

    /**
     * 所有团队Map
     */
    private static final Map<Integer, Team> TEAM_MAP =
            Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * 玩家状态Map
     */
    private static final Map<Integer, PlayerStatus> PLAYER_STATUS_MAP =
            Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * 团队地图
     */
    private static final Map<Integer, List<String>> TEAM_MAP_MAP = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * 团队计数
     */
    private static int teamCount = 0;

    /**
     * 私有构造方法
     */
    private TeamManager() {
        TeamWatcher teamWatcher = new TeamWatcher();
        new Thread(teamWatcher).start();
    }

    /**
     * 随机实例
     */
    private static final Random RANDOM = new Random();

    /**
     * 初始化
     */
    public static void init() {
        LOGGER.info("TeamManager initialized.");
    }

    /**
     * 返回所有团队Map
     *
     * @return 所有团队Map
     */
    static Map<Integer, Team> getTeamMap() {
        return TEAM_MAP;
    }

    /**
     * 返回团队地图Map
     * @return 团队地图Map
     */
    static Map<Integer, List<String>> getTeamMapMap() {
        return TEAM_MAP_MAP;
    }

    /**
     * 返回玩家状态Map
     *
     * @return 玩家状态Map
     */
    static Map<Integer, PlayerStatus> getPlayerStatusMap() {
        return PLAYER_STATUS_MAP;
    }

    /**
     * 获取指定队伍对象
     *
     * @param teamId 队伍id
     * @return 队伍对象
     */
    public static Team get(final int teamId) {
        return TEAM_MAP.get(teamId);
    }

    /**
     * 返回所有队伍列表
     *
     * @return 所有队伍列表
     */
    public static Team[] listTeam() {
        List<Team> list = new ArrayList<>();
        for (Map.Entry<Integer, Team> entry : TEAM_MAP.entrySet()) {
            if (entry.getValue().getOpen()) {
                list.add(entry.getValue());
            }
        }
        return list.toArray(new Team[list.size()]);
    }

    /**
     * 创建队伍
     *
     * @param createPlayerId 创建玩家id
     * @return 创建的队伍
     */
    public static Team create(final int createPlayerId, final int avator, final int coin) {
        //创建队伍
        Team team = new Team(teamCount, createPlayerId, avator, coin);
        LOGGER.info("create team: " + team);
        TEAM_MAP.put(teamCount, team);
        TEAM_MAP_MAP.put(teamCount, new ArrayList<>());
        teamCount++;
        return team;
    }

    /**
     * 删除队伍
     *
     * @param teamId 需要删除的队伍id
     * @return 若删除成功，返回0；否则返回1
     */
    public static int remove(final Integer teamId) {
        if (TEAM_MAP.remove(teamId) != null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 根据玩家id取得队伍成员id列表
     *
     * @param playerId 玩家id
     * @return 队伍成员id列表
     */
    public static Integer[] getPlayersByPlayerId(final int playerId) {
        //获得迭代器
        Iterator<Map.Entry<Integer, Team>> iterator =
                TeamManager.getTeamMap().entrySet().iterator();
        //遍历
        while (iterator.hasNext()) {
            Team team = iterator.next().getValue();
            for (int i = 0; i < team.getPlayers().size(); i++) {
                if (team.getPlayers().get(i) == playerId) {
                    return team.getPlayers().toArray(new Integer[team.getPlayers().size()]);
                }
            }
        }
        return null;
    }

    /**
     * 根据队伍id取得队伍成员id数组，并在结果中排除某个成员id
     *
     * @param teamId         队伍id
     * @param ExceptPlayerId 需要排除的成员id
     * @return 队伍成员id数组
     */
    public static Integer[] getPlayersByTeamIdExcept(final Integer teamId, final int ExceptPlayerId) {
        //完整成员id列表/
        List<Integer> list = TEAM_MAP.get(teamId).getPlayers();
        list.remove(new Integer(ExceptPlayerId));
        return list.toArray(new Integer[list.size()]);
    }

    /**
     * 根据队伍id取得队伍成员id数组
     *
     * @param teamId 队伍id
     * @return 队伍成员id数组
     */
    public static Integer[] getPlayersByTeamId(final Integer teamId) {
        //完整成员id列表
        List<Integer> list = TEAM_MAP.get(teamId).getPlayers();
        return list.toArray(new Integer[list.size()]);
    }

    /**
     * 客户端推送玩家状态
     *
     * @param playerStatus 玩家状态
     */
    public static void putPlayerStatus(final PlayerStatus playerStatus) {
        PLAYER_STATUS_MAP.put(playerStatus.getId(), playerStatus);
    }

    /**
     * 客户端获取同一队伍玩家状态
     *
     * @param teamId 队伍id
     * @return 同一队伍玩家状态
     */
    public static PlayerStatus[] getPlayerStatusByTeamId(final int teamId, final int ExceptPlayerId) {
        List<PlayerStatus> list = new ArrayList<>();
        for (Integer playerId : getPlayersByTeamIdExcept(teamId, ExceptPlayerId)) {
            list.add(PLAYER_STATUS_MAP.get(playerId));
        }
        return list.toArray(new PlayerStatus[list.size()]);
    }

    /**
     * 更新活跃时间
     * @param teamId 队伍id
     */
    public static void updateActiveTime(final int teamId) {
        TEAM_MAP.get(teamId).setLastActiveTime(System.currentTimeMillis());
    }

    /**
     * 获取地图
     *
     * @param teamId 队伍id
     * @param page   页数
     * @return 地图字符串
     */
    public static String getMap(final int teamId, final int page) {
        List<String> list = TEAM_MAP_MAP.get(teamId);
        StringBuilder sb = new StringBuilder();
        if (list.size() < page * TeamConstant.PLATFORM_NUMBER_PER_PAGE) {
            //列表中地面数量小于请求的数量
            while (list.size() < page * TeamConstant.PLATFORM_NUMBER_PER_PAGE){
                String platform = String.format(
                        TeamConstant.FORMAT_OF_PLATFORM,
                        getPlatformType(),
                        getPropType(),
                        getXPosition()
                );
                sb.append(platform).append(TeamConstant.DIVIDER_BETWEEN_EACH_PLATFORM);
                list.add(platform);
            }
        } else {
            //列表中的地面数量不小于请求的数量
            List<String> sublist = list.subList(
                    (page - 1) * TeamConstant.PLATFORM_NUMBER_PER_PAGE,
                    page * TeamConstant.PLATFORM_NUMBER_PER_PAGE -1
            );
            for (String s : sublist) {
                sb.append(s).append(TeamConstant.DIVIDER_BETWEEN_EACH_PLATFORM);
            }
        }
        //返回字符串
        return sb.toString();
    }

    /**
     * 地图生成：获取地面类型
     *
     * @return 返回地面类型
     */
    private static int getPlatformType() {
        float randomValue = RANDOM.nextFloat();
        if (randomValue < 0.5F) {
            return 1;
        } else if (randomValue >= 0.5F && randomValue < 0.8F) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * 地图生成：获取道具/怪物类型
     * @return 返回道具/怪物类型
     */
    private static int getPropType() {
        float randomValue = RANDOM.nextFloat();
        if (randomValue < 0.05F) {
            return 1;
        } else if (randomValue >= 0.1F && randomValue < 0.11F) {
            return 2;
        } else if (randomValue >= 0.11F && randomValue < 0.13F) {
            return 3;
        } else if (randomValue >= 0.13F && randomValue < 0.14F) {
            return 4;
        } else if (randomValue >= 0.14F && randomValue < 0.16F) {
            return 5;
        } else if (randomValue >= 0.16F && randomValue < 0.17F) {
            return 6;
        } else if (randomValue >= 0.18F && randomValue < 0.19F) {
            return 7;
        } else if (randomValue >= 0.19F && randomValue < 0.2F) {
            return 8;
        } else {
            return 0;
        }
    }

    /**
     * 地图生成：获取x方向位置
     * @return 返回x方向位置
     */
    private static float getXPosition() {
        float randomValue = RANDOM.nextFloat();
        return randomValue < 0.5F ?
                randomValue * TeamConstant.SCENE_WIDTH / 2
                :
                -randomValue * TeamConstant.SCENE_WIDTH / 2;
    }
}

class TeamWatcher implements Runnable {
    /**
     * 日志记录器实例
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamWatcher.class);

    @Override
    public void run() {
        LOGGER.info("TeamWatcher running");
        while (true) {
            Iterator<Map.Entry<Integer, Team>> iterator =
                    TeamManager.getTeamMap().entrySet().iterator();

            while (iterator.hasNext()) {
                Team team = iterator.next().getValue();
                if (System.currentTimeMillis() - team.getLastActiveTime() > TeamConstant.EMPTY_TEAM_WATCHER_SLEEP_TIME_IN_MILLIS) {
                    //队伍已经过期
                    LOGGER.info("remove: " + team);
                    //删除地图
                    TeamManager.getTeamMapMap().remove(team.getId());
                    //删除队伍
                    iterator.remove();
                }
            }
            //睡眠
            try {
                Thread.sleep(TeamConstant.EMPTY_TEAM_WATCHER_SLEEP_TIME_IN_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
