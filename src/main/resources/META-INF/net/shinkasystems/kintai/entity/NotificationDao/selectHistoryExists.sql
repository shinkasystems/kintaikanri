select
	exists (
		select
			*
		from
			NOTIFICATION
			inner join USER APPLICANT
				on NOTIFICATION.APPLICANT_ID = APPLICANT.ID
		where
			APPLICANT.ID = /* userID */1
			or APPLICANT.AUTHORITY_ID = /* userID */1
	) as HISTORY_EXISTS
