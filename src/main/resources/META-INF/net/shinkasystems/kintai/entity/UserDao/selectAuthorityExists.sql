select
	exists (
		select
			*
		from
			USER
		where
			USER.ID <> /* userID */1
			and USER.AUTHORITY_ID = /* userID */1
	) as AUTHORITY_EXISTS
