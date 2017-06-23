package yz.doodlejump.constant;

/**
 * 团队常量
 */
public class TeamConstant {
    /**
     * 每个队伍里最多玩家数量
     */
    public static final int MAX_NUMBER_OF_PLAYER_IN_A_TEAM = 3;

    /**
     * 空队伍检查时间间隔
     */
    public static final long EMPTY_TEAM_WATCHER_SLEEP_TIME_IN_MILLIS = 1000 * 60;

    /**
     * 每页地图的数量
     */
    public static final int PLATFORM_NUMBER_PER_PAGE = 400;

    /**
     * 每个地面的字符串格式
     */
    public static final String FORMAT_OF_PLATFORM = "%d,%d,%3.2f";

    /**
     * 每个地面的字符串之间的分割符
     */
    public static final String DIVIDER_BETWEEN_EACH_PLATFORM = "#";

    /**
     * 客户端屏幕宽度
     */
    public static final float SCENE_WIDTH = 5.6F;

    /**
     * 队伍过期时间
     */
    public static final long EXPIRE_TIME_OF_TEAM_IN_MILLIS = 1000 * 60 * 10;
}
