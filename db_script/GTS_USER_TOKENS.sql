use gts_service_platform_db_dev;

CREATE TABLE GTS_USER_TOKENS
(
	USER_TOKEN_PK BIGINT NOT NULL PRIMARY KEY,
	USER_ID BIGINT NOT NULL UNIQUE,
	USER_TOKEN VARCHAR(500) NOT NULL
)