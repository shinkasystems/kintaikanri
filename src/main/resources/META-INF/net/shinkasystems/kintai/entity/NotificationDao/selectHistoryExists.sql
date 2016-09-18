select
	exists (
		select
			*
		from
			NOTIFICATION
		where
			NOTIFICATION.APPLICANT_ID = /* userID */1
			or NOTIFICATION.AUTHORITY_ID = /* userID */1
	) as HISTORY_EXISTS
