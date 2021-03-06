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
    from mobilechallengeuser m right join challenge c on m.username = c.challenger
         matches ma,
         game g,
         outcome o
     where m right join c on c.challenger = m.username
            and c.id = ma.challenge_id
            and ma.id = g.match_id
            and g.id = o.game_id;
            
            
select * from challenge;

select * from mobilechallengeuser;

select * from matches;

select * from mobilechallengeuser m left join challenge c on m.username = c.challenger;