//exemplaires d''jeu disponibles et louables
select e.codebarre, j.* from jeux j inner join exemplaires e
on j.no_jeu = e.no_jeu
where j.reference = 'refWelcome'
and e.louable = 1
and e.no_exemplaire not in 
(select l.no_exemplaire from locations l
where l.date_retour IS null 
and l.no_exemplaire = e.no_exemplaire)
