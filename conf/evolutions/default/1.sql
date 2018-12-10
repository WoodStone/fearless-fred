# Bin SCHEMA

# --- !Ups

CREATE TABLE lan (
  id UUID NOT NULL,
  alias VARCHAR(32) NOT NULL,
  name VARCHAR(64) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE article (
  id UUID NOT NULL,
  lanid UUID NOT NULL,
  timestamp TIMESTAMP NOT NULL,
  header TEXT NOT NULL,
  body TEXT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(lanid) REFERENCES lan(id) ON UPDATE CASCADE
);

CREATE TABLE sponsor (
  id UUID NOT NULL,
  name VARCHAR(64) NOT NULL,
  description TEXT NOT NULL,
  imagesrc VARCHAR(256),
  PRIMARY KEY(id)
);

CREATE TABLE team(
  id UUID NOT NULL,
  name VARCHAR(64) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE teammember(
  teamid UUID NOT NULL,
  userid UUID NOT NULL,
  PRIMARY KEY(teamid, userid),
  FOREIGN KEY (teamid) REFERENCES team(id) ON UPDATE CASCADE
);


--CREATE TABLE lanarticle(
--  articleid UUID NOT NULL,
--  lanid UUID NOT NULL,
--  PRIMARY KEY(articleid, lanid),
--  FOREIGN KEY(articleid) REFERENCES article(id) ON UPDATE CASCADE,
--  FOREIGN KEY(lanid) REFERENCES lan(id) ON UPDATE CASCADE
--);

# --- !Downs
--DROP TABLE lanarticle
DROP TABLE article;
DROP TABLE lan;
DROP TABLE sponsor;

DROP TABLE teammember;
DROP TABLE team;

