select
	NOTIFICATION.ID,
	NOTIFICATION.TERM,
	NOTIFICATION.TYPE,
	NOTIFICATION.COMMENT_APPLYCANT,
	NOTIFICATION.CREATE_DATE,
	USER.DISPLAY_NAME as APPLICANT_DISPLAY_NAME,
	NOTIFICATION.STATUS
from
	NOTIFICATION
	inner join USER
		on NOTIFICATION.APPLICANT_ID = USER.ID
where
/*%if from != null */
    NOTIFICATION.TERM >= /* from */0
/*%end*/
/*%if to != null */
    and NOTIFICATION.TERM <= /* to */0
/*%end*/
/*%if applicantID != null */
    and USER.ID = /* applicantID */0
/*%end*/
/*%if status != null */
    and NOTIFICATION.STATUS = /* status */0
/*%end*/
/*# orderBy */
