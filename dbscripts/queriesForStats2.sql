select m.username,
       m.email,
       m.first_name,
       m.last_name,
       c.challenger,
       c.challengee,
       o.challenger_score,
       o.challengee_score,
       case when(o.challengee_score > o.challenger_score)
        then "W"
        else "L"
       end "Result"
    from mobilechallengeuser m, 
         challenge c,
         matches ma,
         game g,
         outcome o
     where m.username = c.challengee
            and c.id = ma.challenge_id
            and ma.id = g.match_id
            and g.id = o.game_id;
            
            
select * from challenge;

select * from mobilechallengeuser;

select * from matches;