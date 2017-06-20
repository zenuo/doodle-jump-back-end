package yz.doodlejump.service;

import yz.doodlejump.core.SessionManager;
import yz.doodlejump.core.TeamManager;
import yz.doodlejump.entity.DTO.PlayerStatus;
import yz.doodlejump.entity.bean.Session;
import yz.doodlejump.entity.bean.Team;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 队伍服务
 */
@Path("team")
public class TeamService {

    /**
     * 列出所有队伍
     *
     * @param sessionId 会话id
     * @return 所有队伍
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Team[] list(@QueryParam("session") final String sessionId) {
        return TeamManager.listTeam();
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
    public Team create(@QueryParam("session") final String sessionId) {
        return TeamManager.createTeam(SessionManager.getPlayerIdBySessionId(sessionId));
    }

    /**
     * 客户端推送玩家状态
     *
     * @param playerStatus 玩家状态对象
     * @return 状态
     */
    @Path("push")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int push(final PlayerStatus playerStatus) {
        TeamManager.putPlayerStatus(playerStatus);
        return 0;
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
        Session session = SessionManager.getSession(sessionId);
        return TeamManager.getPlayerStatusByTeamId(session.getTeamId());
    }
}
