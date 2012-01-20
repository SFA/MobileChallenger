select * from mobilechallengeuser;

select * from challenge;

select * from matches;

insert into mobilechallengeuser values ('jgyselinck', 'pass','john.gyselinck@sparcedge.com', 'John', 'Gyselinck');
insert into mobilechallengeuser values ('sahlers', 'sahlers', 'steve.ahlers@sparcedge.com', 'Steve', 'Ahlers');
insert into mobilechallengeuser values ('jblow', 'pass', 'john.gyselinck@sparcedge.com', 'Steve', 'Ahlers');

insert into challenge (challengee, challenger) values ('sahlers', 'jgyselinck');
insert into challenge (challengee, challenger) values ('jgyselinck', 'sahlers');
insert into challenge (challengee, challenger) values ('jblow', 'jgyselinck');
insert into challenge (challengee, challenger) values ('sahlers', 'jblow');
insert into challenge (challengee, challenger) values ('jgyselinck', 'jblow');
insert into challenge (challengee, challenger) values ('jblow', 'sahlers');

insert into matches (challenge_id) values (1);
insert into matches (challenge_id) values (2);
insert into matches (challenge_id) values (3);
insert into matches (challenge_id) values (4);
insert into matches (challenge_id) values (5);
insert into matches (challenge_id) values (6);

insert into game (match_id) values (1);
insert into game (match_id) values (2);
insert into game (match_id) values (3);
insert into game (match_id) values (4);
insert into game (match_id) values (5);
insert into game (match_id) values (6);

insert into outcome(game_id, challenger_score, challengee_score) values (1, 21, 19);
insert into outcome(game_id, challenger_score, challengee_score) values (2, 10, 21);
insert into outcome(game_id, challenger_score, challengee_score) values (3, 8, 21);
insert into outcome(game_id, challenger_score, challengee_score) values (4, 21, 12);
insert into outcome(game_id, challenger_score, challengee_score) values (5, 5, 21);
insert into outcome(game_id, challenger_score, challengee_score) values (6, 21, 17);





