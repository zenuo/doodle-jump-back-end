package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.entity.bean.Team;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 队伍管理类
 */
public final class TeamManager {
    private static final TeamManager INSTANCE = new TeamManager();
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamManager.class);

    private static final Map<Integer, Team> teamMap =
            Collections.synchronizedMap(new LinkedHashMap<>());



}
