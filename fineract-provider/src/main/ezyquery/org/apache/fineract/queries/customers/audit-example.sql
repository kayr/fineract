--
-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements. See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership. The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License. You may obtain a copy of the License at
--
-- http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an
-- "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
-- KIND, either express or implied. See the License for the
-- specific language governing permissions and limitations
-- under the License.
--


SELECT aud.id AS id
    ,aud.action_name AS actionName
    ,aud.entity_name AS entityName
    ,aud.resource_id AS resourceId
    ,aud.subresource_id AS subresourceId
    ,aud.client_id AS clientId
    ,aud.loan_id AS loanId
    ,mk.username AS maker
    ,aud.made_on_date AS madeOnDate
    ,aud.made_on_date_utc AS madeOnDateUTC
    ,aud.api_get_url AS resourceGetUrl
    ,ck.username AS checker
    ,aud.checked_on_date AS checkedOnDate
    ,aud.checked_on_date_utc AS checkedOnDateUTC
    ,ev.enum_message_property AS processingResult
    ,o.name AS officeName
    ,gl.level_name AS groupLevelName
    ,g.display_name AS groupName
    ,c.display_name AS clientName
    ,l.account_no AS loanAccountNo
    ,s.account_no AS savingsAccountNo
FROM m_portfolio_command_source aud
LEFT JOIN m_appuser mk ON mk.id = aud.maker_id
LEFT JOIN m_appuser ck ON ck.id = aud.checker_id
LEFT JOIN m_office o ON o.id = aud.office_id
LEFT JOIN m_group g ON g.id = aud.group_id
LEFT JOIN m_group_level gl ON gl.id = g.level_id
LEFT JOIN m_client c ON c.id = aud.client_id
LEFT JOIN m_loan l ON l.id = aud.loan_id
LEFT JOIN m_savings_account s ON s.id = aud.savings_account_id
LEFT JOIN r_enum_value ev ON ev.enum_name = 'status'
    AND ev.enum_id = aud.STATUS
