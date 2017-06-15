package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.constant.TeamConstant;
import yz.doodlejump.entity.bean.Team;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 队伍管理类
 */
public final class TeamManager {
    private static final TeamManager INSTANCE = new TeamManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamManager.class);

    private static final Map<Integer, Team> TEAM_MAP =
            Collections.synchronizedMap(new LinkedHashMap<>());

    private static int teamCount = 0;

    private TeamManager() {
        EmptyTeamWatcher emptyTeamWatcher = new EmptyTeamWatcher();
        new Thread(emptyTeamWatcher).start();
    }

    public static void init() {
        LOGGER.info("TeamManager initialized.");
    }

    static Map<Integer, Team> getTeamMap() {
        return TEAM_MAP;
    }

    public static Integer createTeam(Integer createPlayerId) {
        Team team = new Team(teamCount, createPlayerId);
        TEAM_MAP.put(teamCount, team);
        LOGGER.info("create team: " + team);
        teamCount++;
        return team.getId();
    }

    /**
     * 删除队伍
     *
     * @param teamId 需要删除的队伍id
     * @return 若删除成功，返回0；否则返回1
     */
    public static int removeTeam(Integer teamId) {
        if (TEAM_MAP.remove(teamId) != null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 根据玩家id取得队伍成员id列表
     * @param playerId 玩家id
     * @return 队伍成员id列表
     */
    public static Integer[] getPlayersByPlayerId(int playerId) {
        Iterator<Map.Entry<Integer, Team>> iterator =
                TeamManager.getTeamMap().entrySet().iterator();
        while (iterator.hasNext()) {
            Team team = iterator.next().getValue();
            for (int i = 0; i < team.getPlayers().size(); i++) {
                if (team.getPlayers().get(i) == playerId) {
                    return (Integer[]) team.getPlayers().toArray();
                }
            }
        }
        return null;
    }
}

class EmptyTeamWatcher implements Runnable {
    /**
     * 日志记录器实例
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmptyTeamWatcher.class);

    @Override
    public void run() {
        LOGGER.info("EmptyTeamWatcher running");
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
