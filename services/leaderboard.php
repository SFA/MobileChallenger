<?php
    $leaderQuery = "select * from (
                        Select username,
                               email,
                               first_name,
                               last_name,
                               sum(
                                   case when(s.result = 'W')
                                            then 1
                                            else 0
                                   end
                                ) 'Wins',
                               sum(
                                   case when(s.result = 'L')
                                            then 1
                                            else 0
                                   end
                                ) 'Losses'
                        from (
                        (select m.username,
                               m.email,
                               m.first_name,
                               m.last_name,
                               c.challenger,
                               c.challengee,
                               o.challenger_score,
                               o.challengee_score,
                               case when(o.challenger_score > o.challengee_score)
                                then 'W'
                                else 'L'
                               end 'Result'
                            from mobilechallengeuser m,
                                 challenge c,
                                 matches ma,
                                 game g,
                                 outcome o
                             where m.username = c.challenger
                                    and c.id = ma.challenge_id
                                    and ma.id = g.match_id
                                    and g.id = o.game_id)
                        union all
                        (select m.username,
                               m.email,
                               m.first_name,
                               m.last_name,
                               c.challenger,
                               c.challengee,
                               o.challenger_score,
                               o.challengee_score,
                               case when(o.challengee_score > o.challenger_score)
                                then 'W'
                                else 'L'
                               end 'Result'
                            from mobilechallengeuser m,
                                 challenge c,
                                 matches ma,
                                 game g,
                                 outcome o
                             where m.username = c.challengee
                                    and c.id = ma.challenge_id
                                    and ma.id = g.match_id
                                    and g.id = o.game_id)
                        ) s
                        group by s.username
                        ) t order by t.Wins desc,
                                     t.Losses asc";

    mysql_connect("localhost", "mobilechallenger", "sparcmobile");
    mysql_select_db("mobilechallenger");

    $result = mysql_query($leaderQuery);
    if(!$result){
        die('Invalid query: '.mysqli_error());
    }

    $results = array();
    while ($row = mysql_fetch_assoc($result)){
        $results[] = $row;
    }

    header("Content-type: application/json");
    echo json_encode($results);

    mysql_free_result($result);
?>