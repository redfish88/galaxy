CREATE TABLE IF NOT EXISTS race(
match_id          bigint   UNIQUE not null  comment 'replay id' ,
dt                datetime  comment '日期yyyyMMdd格式',
start_time        bigint   comment '开始时间',
end_time          bigint   comment '结束时间',
duration          bigint   comment '时长',
game_mode         int      comment  '模式',
radiant_team_id   int    default 2  comment '天辉id',
dire_team_id      int    default 3  comment '夜魇id',
won_team_id       int      comment '胜利方id',
primary key(`match_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS players (
id                int      auto_increment,
steam_id           varchar(32)   UNIQUE comment 'steam_id',
avatar            varchar(255)  comment '头像',
nick_name         varchar(255)  comment '昵称',
primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS heroes(
  id                int           UNIQUE PRIMARY KEY,
  name              varchar(255)  comment '英雄名称',
  localized_name    varchar(255)  comment '本地化名称',
  avatar            varchar(255)  comment '头像'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS match_logs (
dt                datetime default null      comment '日期yyyyMMdd格式',
match_id          bigint    not null  comment 'replay id' ,
steam_id          varchar(32) comment 'steamid',
player_name        varchar(255) comment '玩家名称',
player_nick_name   varchar(255) comment '玩家别名',
team_id           int    comment    '所在队伍id 2 天晖 3 夜魇',
team_solt         int    comment    '在队伍中的顺位',
level             int    default 1  comment '等级',
kills             int    default 0  comment '击杀数' ,
deaths            int    default 0  comment '死亡数' ,
assists           int    default 0  comment '助攻数',
gold              int    default 0  comment '金钱',
lh                int    default 0  comment '正补',
dn                int    default 0  comment '反补',
hero_id           int    default 0  comment '英雄id',
hero_name         varchar(255) default '' comment '英雄名称',
hero_damage       bigint default 0  comment '英雄伤害',
tower_damage      bigint default 0  comment '建筑伤害',
killed_by         varchar(255)      comment '被杀集合',
multi_kills       varchar(255)      comment '击杀集合',
UNIQUE (match_id,steam_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS replay_file (
id                int      auto_increment,
random_name       varchar(255)  comment '生成随机名',
original_name     varchar(255)  comment '上传文件原名',
abs_path          varchar(255)  comment '绝对路径地址',    
create_time       datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `matches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `win` varchar(255) DEFAULT NULL,
  `r_h_1` varchar(255) DEFAULT NULL,
  `r_h_2` varchar(255) DEFAULT NULL,
  `r_h_3` varchar(255) DEFAULT NULL,
  `r_h_4` varchar(255) DEFAULT NULL,
  `r_h_5` varchar(255) DEFAULT NULL,
  `d_h_1` varchar(255) DEFAULT NULL,
  `d_h_2` varchar(255) DEFAULT NULL,
  `d_h_3` varchar(255) DEFAULT NULL,
  `d_h_4` varchar(255) DEFAULT NULL,
  `d_h_5` varchar(255) DEFAULT NULL,
  `r_p_1`  varchar(255) DEFAULT NULL,
  `r_p_2`  varchar(255) DEFAULT NULL,
  `r_p_3`  varchar(255) DEFAULT NULL,
  `r_p_4`  varchar(255) DEFAULT NULL,
  `r_p_5`  varchar(255) DEFAULT NULL,
  `d_p_1`  varchar(255) DEFAULT NULL,
  `d_p_2`  varchar(255) DEFAULT NULL,
  `d_p_3`  varchar(255) DEFAULT NULL,
  `d_p_4`  varchar(255) DEFAULT NULL,
  `d_p_5`  varchar(255) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedAt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `type` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;