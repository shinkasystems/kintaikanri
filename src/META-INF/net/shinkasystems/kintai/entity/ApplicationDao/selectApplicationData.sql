select
	APPLICATION.ID,
	APPLICATION.TERM,
	APPLICATION.TYPE,
	APPLICATION.COMMENT_APPLYCANT,
	APPLICATION.CREATE_DATE,
	USER.DISPLAY_NAME as APPLICANT_DISPLAY_NAME,
	APPLICATION.STATUS
from
	APPLICATION
	inner join USER
		on APPLICATION.APPLICANT_ID = USER.ID
where
/*%if from != null */
    APPLICATION.TERM >= /* from */0
/*%end*/
/*%if to != null */
    and APPLICATION.TERM <= /* to */0
/*%end*/
/*%if applicantID != null */
    and USER.ID = /* applicantID */0
/*%end*/
/*%if status != null */
    and APPLICATION.STATUS = /* status */0
/*%end*/
/*# orderBy */
