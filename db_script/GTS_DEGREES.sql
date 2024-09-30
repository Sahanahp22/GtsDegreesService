create table GTS_DEGREES(
  gts_degree_id           BIGINT  NOT NULL    PRIMARY KEY    ,
  gts_degree_name         VARCHAR(50)  NOT NULL UNIQUE,
  gts_degree_description  VARCHAR(200) ,
  gts_degree_status       BIT NOT NULL
  
)