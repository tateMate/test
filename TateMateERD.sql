DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
	`user_uid`	int	NOT NULL,
	`user_id`	varchar(50)	NOT NULL,
	`user_pw`	varchar(15)	NOT NULL,
	`user_nickname`	varchar(50)	NOT NULL,
	`user_gender`	bool	NOT NULL,
	`user_nationality`	varchar(50)	NOT NULL,
	`user_age`	tinyint	NOT NULL,
	`user_smoking`	bool	NOT NULL,
	`user_vaccine`	bool	NOT NULL,
	`user_room`	bool	NOT NULL,
	`user_matching`	bool	NOT NULL,
	`user_pet`	bool	NULL,
	`user_intro`	varchar(500)	NULL,
	`user_ideal`	varchar(500)	NULL,
	`user_location`	varchar(50)	NULL,
	`user_profile`	varchar(255)	NULL,
	`user_sns`	varchar(50)	NULL
);

DROP TABLE IF EXISTS `coment`;

CREATE TABLE `coment` (
	`coment_id`	int	NOT NULL,
	`coment_uid_to`	int	NOT NULL,
	`coment_uid_from`	int	NOT NULL,
	`coment_contents`	varchar(500)	NOT NULL,
	`coment_time`	timestamp	NOT NULL,
	`coment_access`	bool	NOT NULL,
	`coment_status`	tinyint	NOT NULL	DEFAULT 1
);

DROP TABLE IF EXISTS `cocoment`;

CREATE TABLE `cocoment` (
	`cocoment_id`	int	NOT NULL,
	`coment_id`	int	NOT NULL,
	`cocoment_uid_from`	int	NOT NULL,
	`cocoment_contents`	varchar(500)	NOT NULL,
	`cocoment_time`	timestamp	NOT NULL,
	`cocoment_status`	tinyint	NOT NULL	DEFAULT 1
);

DROP TABLE IF EXISTS `user_character`;

CREATE TABLE `user_character` (
	`user_uid`	int	NOT NULL,
	`cleanliness`	tinyint	NOT NULL,
	`wakeup_time`	tinyint	NOT NULL,
	`sleep_time`	tinyint	NOT NULL,
	`cooking_frequency`	tinyint	NOT NULL,
	`chatter`	tinyint	NOT NULL,
	`snoring`	tinyint	NOT NULL,
	`mbti`	varchar(4)	NOT NULL
);

DROP TABLE IF EXISTS `intro_image`;

CREATE TABLE `intro_image` (
	`image_id`	int	NOT NULL,
	`user_uid`	int	NOT NULL,
	`image_address`	varchar(255)	NOT NULL
);

ALTER TABLE `USER` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_uid`
);

ALTER TABLE `coment` ADD CONSTRAINT `PK_COMENT` PRIMARY KEY (
	`coment_id`
);

ALTER TABLE `cocoment` ADD CONSTRAINT `PK_COCOMENT` PRIMARY KEY (
	`cocoment_id`
);

ALTER TABLE `user_character` ADD CONSTRAINT `PK_USER_CHARACTER` PRIMARY KEY (
	`user_uid`
);

ALTER TABLE `intro_image` ADD CONSTRAINT `PK_INTRO_IMAGE` PRIMARY KEY (
	`image_id`
);

ALTER TABLE `coment` ADD CONSTRAINT `FK_USER_TO_coment_1` FOREIGN KEY (
	`coment_uid_to`
)
REFERENCES `USER` (
	`user_uid`
);

ALTER TABLE `coment` ADD CONSTRAINT `FK_USER_TO_coment_2` FOREIGN KEY (
	`coment_uid_from`
)
REFERENCES `USER` (
	`user_uid`
);

ALTER TABLE `cocoment` ADD CONSTRAINT `FK_coment_TO_cocoment_1` FOREIGN KEY (
	`coment_id`
)
REFERENCES `coment` (
	`coment_id`
);

ALTER TABLE `cocoment` ADD CONSTRAINT `FK_USER_TO_cocoment_1` FOREIGN KEY (
	`cocoment_uid_from`
)
REFERENCES `USER` (
	`user_uid`
);

ALTER TABLE `user_character` ADD CONSTRAINT `FK_USER_TO_user_character_1` FOREIGN KEY (
	`user_uid`
)
REFERENCES `USER` (
	`user_uid`
);

ALTER TABLE `intro_image` ADD CONSTRAINT `FK_USER_TO_intro_image_1` FOREIGN KEY (
	`user_uid`
)
REFERENCES `USER` (
	`user_uid`
);

