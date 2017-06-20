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
     * 返回玩家状态Map
     *
     * @return 玩家状态Map
     */
    static Map<Integer, PlayerStatus> getPlayerStatusMap() {
        return PLAYER_STATUS_MAP;
    }

    /**
     * 返回所有队伍列表
     *
     * @return 所有队伍列表
     */
    public static Team[] listTeam() {
        Team[] teams = new Team[TEAM_MAP.size()];
        int i = 0;
        for (Map.Entry<Integer, Team> entry : TEAM_MAP.entrySet()) {
            teams[i] = entry.getValue();
            i++;
        }
        return teams;
    }

    /**
     * 创建队伍
     *
     * @param createPlayerId 创建玩家id
     * @return 创建的队伍
     */
    public static Team createTeam(final Integer createPlayerId) {
        Team team = new Team(teamCount, createPlayerId);
        TEAM_MAP.put(teamCount, team);
        LOGGER.info("create team: " + team);
        teamCount++;
        return team;
    }


    /**
     * 删除队伍
     *
     * @param teamId 需要删除的队伍id
     * @return 若删除成功，返回0；否则返回1
     */
    public static int removeTeam(final Integer teamId) {
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
     * 根据队伍id取得队伍成员id列表
     *
     * @param teamId 队伍id
     * @return 队伍成员id列表
     */
    public static Integer[] getPlayersByTeamId(final Integer teamId) {
        return (Integer[]) TEAM_MAP.get(teamId).getPlayers().toArray();
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
    public static PlayerStatus[] getPlayerStatusByTeamId(final Integer teamId) {
        List<PlayerStatus> list = new ArrayList<>();
        for (Integer playerId : getPlayersByTeamId(teamId)) {
            list.add(PLAYER_STATUS_MAP.get(playerId));
        }
        return list.toArray(new PlayerStatus[list.size()]);
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
                if (team.getPlayers().size() == 0) {
                    LOGGER.info("remove: " + team);
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
