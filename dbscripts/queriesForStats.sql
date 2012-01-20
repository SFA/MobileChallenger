select * 
    from mobilechallengeuser m, 
         challenge c 
     where m.username = c.challenger;-- or m.username = m.challengee);