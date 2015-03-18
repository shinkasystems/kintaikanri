select
	exists (
		select
			*
		from
			APPLICATION
			inner join USER APPLICANT
				on APPLICATION.APPLICANT_ID = APPLICANT.ID
		where
			APPLICANT.ID = /* userID */1
			or APPLICANT.AUTHORITY_ID = /* userID */1
	) as HISTORY_EXISTS
