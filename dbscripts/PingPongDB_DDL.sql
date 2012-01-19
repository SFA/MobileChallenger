-- Generated by Oracle SQL Developer Data Modeler 3.0.0.665
--   at:        2012-01-16 16:00:13 EST
--   site:      Oracle Database 10g
--   type:      Oracle Database 10g



CREATE TABLE Challenge 
    ( 
     id BIGINT  NOT NULL , 
     challengee VARCHAR(50)  NOT NULL , 
     challenger VARCHAR(50)  NOT NULL 
    ) 
;



ALTER TABLE Challenge 
    ADD CONSTRAINT Challenge_PK PRIMARY KEY ( id ) ;


CREATE TABLE Game 
    ( 
     id BIGINT  NOT NULL , 
     match_id BIGINT  NOT NULL 
    ) 
;



ALTER TABLE Game 
    ADD CONSTRAINT Game_PK PRIMARY KEY ( id ) ;


CREATE TABLE Matches 
    ( 
     id BIGINT  NOT NULL , 
     challenge_id BIGINT  NOT NULL , 
     result VARCHAR(15) 
    ) 
;



ALTER TABLE Matches 
    ADD CONSTRAINT Match_PK PRIMARY KEY ( id ) ;


CREATE TABLE Outcome 
    ( 
     id BIGINT  NOT NULL , 
     game_id BIGINT  NOT NULL , 
     result VARCHAR(15) , 
     challenger_score BIGINT , 
     challengee_score BIGINT 
    ) 
;



ALTER TABLE Outcome 
    ADD CONSTRAINT Outcome_PK PRIMARY KEY ( id ) ;


CREATE TABLE MobileChallengeUser 
    ( 
     username VARCHAR(50)  NOT NULL ,
     password VARCHAR(50) NOT NULL,
     email VARCHAR(150)  NOT NULL , 
     first_name VARCHAR(50) , 
     last_name VARCHAR(50) 
    ) 
;



ALTER TABLE MobileChallengeUser 
    ADD CONSTRAINT User_PK PRIMARY KEY ( username ) ;



ALTER TABLE Challenge 
    ADD CONSTRAINT Challenge_User_challengee_FK FOREIGN KEY 
    ( 
     challengee
    ) 
    REFERENCES MobileChallengeUser 
    ( 
     username
    ) 
;


ALTER TABLE Challenge 
    ADD CONSTRAINT Challenge_User_challenger_FK FOREIGN KEY 
    ( 
     challenger
    ) 
    REFERENCES MobileChallengeUser 
    ( 
     username
    ) 
;


ALTER TABLE Game 
    ADD CONSTRAINT Game_Match_FK FOREIGN KEY 
    ( 
     match_id
    ) 
    REFERENCES Matches 
    ( 
     id
    ) 
;


ALTER TABLE Matches 
    ADD CONSTRAINT Match_Challenge_FK FOREIGN KEY 
    ( 
     challenge_id
    ) 
    REFERENCES Challenge 
    ( 
     id
    ) 
;


ALTER TABLE Outcome 
    ADD CONSTRAINT Outcome_Game_FK FOREIGN KEY 
    ( 
     game_id
    ) 
    REFERENCES Game 
    ( 
     id
    ) 
;








-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             5
-- CREATE INDEX                             0
-- ALTER TABLE                             10
-- CREATE VIEW                              0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- CREATE STRUCTURED TYPE                   0
-- CREATE COLLECTION TYPE                   0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
