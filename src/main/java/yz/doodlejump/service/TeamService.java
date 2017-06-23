package yz.doodlejump.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.core.SessionManager;
import yz.doodlejump.core.TeamManager;
import yz.doodlejump.entity.DTO.PlayerStatus;
import yz.doodlejump.entity.DTO.TeamDTO;
import yz.doodlejump.entity.bean.Team;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 队伍服务
 */
@Path("team")
public class TeamService {
    //日志记录对象
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    /**
     * 获取队伍对象
     *
     * @param sessionId 会话id
     * @param teamId    队伍id
     * @return 队伍对象
     */
    @Path("get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Team get(@QueryParam("session") final String sessionId,
                    @QueryParam("teamId") final int teamId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("get " + sessionId);
            TeamManager.updateActiveTime(teamId);
            return TeamManager.get(teamId);
        } else {
            return null;
        }
    }


    /**
     * 列出所有队伍
     *
     * @param sessionId 会话id
     * @return 所有队伍
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TeamDTO[] list(@QueryParam("session") final String sessionId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("list " + sessionId);
            Team[] teams = TeamManager.listTeam();
            TeamDTO[] teamDTOS = new TeamDTO[teams.length];
            for (int i = 0; i < teams.length; i++) {
                teamDTOS[i] = new TeamDTO(
                        teams[i].getId(),
                        teams[i].getPlayers().size(),
                        teams[i].getOpen(),
                        teams[i].getCreateTime()
                );
            }
            return teamDTOS;
        } else {
            return null;
        }
    }

    /**
     * 创建一个队伍
     *
     * @param sessionId 会话id
     * @return 创建的队伍
     */
    @Path("create")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Team create(@QueryParam("session") final String sessionId,
                       @QueryParam("avator") final int avator,
                       @QueryParam("coin") final int coin) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("create " + sessionId);
            return TeamManager.create(SessionManager.getPlayerIdBySessionId(sessionId), avator, coin);
        } else {
            return null;
        }
    }

    /**
     * 客户端推送玩家状态
     *
     * @param playerStatus 玩家状态对象
     * @return 状态
     */
    @Path("push-{session}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int push(@PathParam("session") final String sessionId,
                    final PlayerStatus playerStatus) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("push " + sessionId);
            int teamId = SessionManager.getSession(sessionId).getTeamId();
            TeamManager.updateActiveTime(teamId);
            TeamManager.putPlayerStatus(playerStatus);
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 客户端获取同个队伍的玩家状态
     *
     * @param sessionId 会话id
     * @return 同个队伍的玩家状态
     */
    @Path("pull")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerStatus[] pull(@QueryParam("session") final String sessionId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("pull " + sessionId);
            int teamId = SessionManager.getSession(sessionId).getTeamId();
            TeamManager.updateActiveTime(teamId);
            return TeamManager.getPlayerStatusByTeamId(
                    teamId,
                    SessionManager.getPlayerIdBySessionId(sessionId)
            );
        } else {
            return null;
        }
    }

    /**
     * 加入队伍
     *
     * @param sessionId 会话id
     * @param teamId    队伍id
     * @return 若加入成功，返回0；否则返回1
     */
    @Path("join")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int join(@QueryParam("session") final String sessionId,
                    @QueryParam("teamId") final int teamId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("join " + teamId + " ,session: " + sessionId);
            TeamManager.updateActiveTime(teamId);
            int playerId = SessionManager.getPlayerIdBySessionId(sessionId);
            return TeamManager.get(teamId).join(playerId);
        } else {
            return 1;
        }
    }

    /**
     * 锁定队伍
     *
     * @param sessionId 会话id
     * @param teamId    队伍id
     * @return 若锁定成功，返回0；否则返回1
     */
    @Path("lock")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int lock(@QueryParam("session") final String sessionId,
                    @QueryParam("teamId") final int teamId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("lock " + teamId + " ,session: " + sessionId);
            TeamManager.updateActiveTime(teamId);
            return TeamManager.get(teamId).lock();
        } else {
            return 1;
        }
    }

    /**
     * 获取地图
     * @param sessionId 会话id
     * @param teamId 队伍id
     * @param page 页数
     * @return 地图字符串
     */
    @Path("map")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String map(@QueryParam("session") final String sessionId,
                      @QueryParam("teamId") final int teamId,
                      @QueryParam("page") final int page) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("map: " + teamId + ", page: " + page);
            TeamManager.updateActiveTime(teamId);
            return TeamManager.getMap(teamId, page);
        } else {
            return null;
        }
    }
}
