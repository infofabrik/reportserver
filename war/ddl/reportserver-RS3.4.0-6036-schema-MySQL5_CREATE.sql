# drop schema if exists reportserver; 
# create schema reportserver default character set utf8 default collate utf8_bin; 
# use reportserver; 



    create table RS_ACE (
        ENTITY_ID bigint not null auto_increment,
        accesstype integer not null,
        n integer not null,
        ENTITY_VERSION integer,
        acl_id bigint not null,
        folk_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACE_2_ACCESS_MAPS (
        ace_id bigint not null,
        access_maps_id bigint not null,
        primary key (ace_id, access_maps_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACE_2_ACCESS_MAPS_A (
        REV integer not null,
        ace_id bigint not null,
        access_maps_id bigint not null,
        revtype tinyint,
        primary key (REV, ace_id, access_maps_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        accesstype integer,
        n integer,
        acl_id bigint,
        folk_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACE_ACCESS_MAP (
        ENTITY_ID bigint not null auto_increment,
        ACCESS_FIELD bigint,
        securee varchar(32),
        ENTITY_VERSION integer,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACE_ACCESS_MAP_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        ACCESS_FIELD bigint,
        securee varchar(32),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACL (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION integer,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ACL_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ADD_COLUMN_SPEC (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ADD_COLUMN_SPEC_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_AUDIT_LOG_ENTRY (
        ENTITY_ID bigint not null auto_increment,
        action varchar(64),
        DATE_FIELD datetime,
        user_id bigint,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_AUDIT_LOG_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(64) not null,
        value longtext,
        ENTITY_VERSION bigint,
        LOG_ENTRY_ID bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BINARY_COLUMN_FILTER (
        operator integer,
        id bigint not null,
        columna_id bigint,
        columnb_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BINARY_COLUMN_FILTER_A (
        id bigint not null,
        REV integer not null,
        operator integer,
        columna_id bigint,
        columnb_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT (
        id bigint not null,
        report_file_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_A (
        id bigint not null,
        REV integer not null,
        report_file_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_DATASRC (
        database_cache integer not null,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_DATASRC_A (
        id bigint not null,
        REV integer not null,
        database_cache integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_DATASRC_CFG (
        query_wrapper varchar(4096),
        target varchar(255),
        target_type integer,
        id bigint not null,
        report_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_DATASRC_CFG_A (
        id bigint not null,
        REV integer not null,
        query_wrapper varchar(4096),
        target varchar(255),
        target_type integer,
        report_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_FILE (
        ENTITY_ID bigint not null auto_increment,
        content longtext,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_FILE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content longtext,
        NAME_FIELD varchar(128),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BIRT_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BLATEXT_PARAM_DEF (
        value longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BLATEXT_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        value longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BLATEXT_PARAM_INST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_BLATEXT_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN (
        ENTITY_ID bigint not null auto_increment,
        aggregate_function integer,
        alias varchar(255),
        dimension varchar(255),
        hidden BIT(1),
        NAME_FIELD varchar(255),
        null_handling integer,
        null_replacement_format varchar(255),
        ORDER_FIELD integer,
        position integer not null,
        preview_width integer,
        subtotal_group BIT(1),
        type integer,
        ENTITY_VERSION bigint,
        filter_id bigint,
        format_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        aggregate_function integer,
        alias varchar(255),
        dimension varchar(255),
        hidden BIT(1),
        NAME_FIELD varchar(255),
        null_handling integer,
        null_replacement_format varchar(255),
        ORDER_FIELD integer,
        position integer,
        preview_width integer,
        subtotal_group BIT(1),
        type integer,
        filter_id bigint,
        format_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FILTER (
        id bigint not null,
        column_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FILTER_A (
        id bigint not null,
        REV integer not null,
        column_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_CURRENCY (
        currency_type integer,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_CURRENCY_A (
        id bigint not null,
        REV integer not null,
        currency_type integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_DATE (
        base_format varchar(255),
        error_replacement varchar(255),
        replace_errors BIT(1),
        roll_over BIT(1),
        target_format varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_DATE_A (
        id bigint not null,
        REV integer not null,
        base_format varchar(255),
        error_replacement varchar(255),
        replace_errors BIT(1),
        roll_over BIT(1),
        target_format varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_NUMBER (
        number_of_decimal_places integer not null,
        thousand_separator BIT(1) not null,
        type integer,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_NUMBER_A (
        id bigint not null,
        REV integer not null,
        number_of_decimal_places integer,
        thousand_separator BIT(1),
        type integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_TEMPLATE (
        template varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_TEMPLATE_A (
        id bigint not null,
        REV integer not null,
        template varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_TEXT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_FORMAT_TEXT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_REFERENCE (
        id bigint not null,
        reference_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COLUMN_REFERENCE_A (
        id bigint not null,
        REV integer not null,
        reference_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COMPILED_REPORT (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        serialized_report longblob,
        ENTITY_VERSION bigint,
        report_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COMPUTED_COLUMN (
        description longtext,
        expression longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_COMPUTED_COLUMN_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        expression longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CONDITION (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        KEY_FIELD varchar(64),
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        report_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CRYSTAL_REPORT (
        id bigint not null,
        report_file_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CRYSTAL_REPORT_A (
        id bigint not null,
        REV integer not null,
        report_file_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CRYSTAL_REPORT_FILE (
        ENTITY_ID bigint not null auto_increment,
        content longblob,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CRYSTAL_REPORT_FILE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content longblob,
        NAME_FIELD varchar(128),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CRYSTAL_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CRYSTAL_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CSV_DATASOURCE (
        database_cache integer not null,
        quote varchar(255),
        SEPARATOR_FIELD varchar(255),
        id bigint not null,
        connector_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CSV_DATASOURCE_A (
        id bigint not null,
        REV integer not null,
        database_cache integer,
        quote varchar(255),
        SEPARATOR_FIELD varchar(255),
        connector_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CSV_DATASOURCE_CONF (
        query_wrapper varchar(4096),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_CSV_DATASOURCE_CONF_A (
        id bigint not null,
        REV integer not null,
        query_wrapper varchar(4096),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET (
        ENTITY_ID bigint not null auto_increment,
        col integer not null,
        container integer,
        height integer not null,
        n integer not null,
        reload_interval bigint not null,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        col integer,
        container integer,
        height integer,
        n integer,
        reload_interval bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_FAVORITE_LIST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_LIBRARY (
        id bigint not null,
        dadget_node_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_PARAMETER (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_REPORT (
        config longtext,
        show_execute_button BIT(1) not null,
        id bigint not null,
        report_id bigint,
        report_reference_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_REPORT_2_PARAM_INST (
        dadget_id bigint not null,
        parameter_instances_id bigint not null,
        primary key (dadget_id, parameter_instances_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_STATIC_HTML (
        data longtext,
        title varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DADGET_URL (
        title varchar(255),
        url varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        layout integer,
        n integer not null,
        NAME_FIELD varchar(255),
        reload_interval bigint not null,
        single_page BIT(1) not null,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_2_DADGET (
        dashboard_id bigint not null,
        dadgets_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_2_DADGET_A (
        REV integer not null,
        dashboard_id bigint not null,
        dadgets_id bigint not null,
        revtype tinyint,
        primary key (REV, dashboard_id, dadgets_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        layout integer,
        n integer,
        NAME_FIELD varchar(255),
        reload_interval bigint,
        single_page BIT(1),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_CONTAINER (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_CONTAINER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_CONT_2_DASHBRD (
        dashboard_container_id bigint not null,
        dashboards_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_CONT_2_DASHBRD_A (
        REV integer not null,
        dashboard_container_id bigint not null,
        dashboards_id bigint not null,
        revtype tinyint,
        primary key (REV, dashboard_container_id, dashboards_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_DADGET_NODE (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        dadget_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_DADGET_NODE_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        dadget_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_DASHBOARD_NODE (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        dashboard_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_DASHBOARD_NODE_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        dashboard_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_FOLDER (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_FOLDER_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_MNGR_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_MNGR_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_REFERENCE (
        id bigint not null,
        dashboard_node_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_REFERENCE_A (
        id bigint not null,
        REV integer not null,
        dashboard_node_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_USER (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        dashboard_container_id bigint,
        user_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DASHBOARD_USER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        dashboard_container_id bigint,
        user_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATABASE_BUNDLE_ENTRY (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(255),
        ENTITY_VERSION bigint,
        database_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATABASE_BUNDLE_ENTRY_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        KEY_FIELD varchar(255),
        database_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATABASE_DATASOURCE (
        database_descriptor varchar(255),
        jdbc_properties longtext,
        password varchar(255),
        url varchar(1024),
        username varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATABASE_DATASOURCE_A (
        id bigint not null,
        REV integer not null,
        database_descriptor varchar(255),
        jdbc_properties longtext,
        password varchar(255),
        url varchar(1024),
        username varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATABASE_DATASOURCE_CONF (
        query longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATABASE_DATASOURCE_CONF_A (
        id bigint not null,
        REV integer not null,
        query longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_CONTAINER (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        datasink_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_CONTAINER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        datasink_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_DEFINITION (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_DEFINITION_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_FOLDER (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_FOLDER_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_MNGR_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASINK_MNGR_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_CONNECTOR (
        DTYPE varchar(31) not null,
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        data longtext,
        url varchar(255),
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_CONNECTOR_A (
        DTYPE varchar(31) not null,
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        url varchar(255),
        data longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_CONNECTOR_CFG (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(255),
        value longtext,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_CONNECTOR_CFG_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        KEY_FIELD varchar(255),
        value longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_CONTAINER (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        datasource_id bigint,
        datasource_config_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_CONTAINER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        datasource_id bigint,
        datasource_config_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_DEFINITION (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_DEFINITION_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_DEF_CONFIG (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_DEF_CONFIG_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_FBCFG_2_DSCC (
        csv_datasource_conf_id bigint not null,
        connector_config_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_FOLDER (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_FOLDER_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_MNGR_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_MNGR_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_PARAMETER_DATA (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(255),
        value varchar(255),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_PARAMETER_DATA_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        KEY_FIELD varchar(255),
        value varchar(255),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_PARAM_DEF (
        box_layout_mode integer,
        box_layout_pack_col_size integer not null,
        box_layout_pack_mode integer,
        format varchar(255),
        height integer not null,
        MODE_FIELD integer,
        multi_selection_mode integer,
        post_process longtext,
        return_type integer,
        single_selection_mode integer,
        width integer not null,
        id bigint not null,
        datasource_container_id bigint,
        s_def_value_simpl_data_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        box_layout_mode integer,
        box_layout_pack_col_size integer,
        box_layout_pack_mode integer,
        format varchar(255),
        height integer,
        MODE_FIELD integer,
        multi_selection_mode integer,
        post_process longtext,
        return_type integer,
        single_selection_mode integer,
        width integer,
        datasource_container_id bigint,
        s_def_value_simpl_data_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_PARAM_INST (
        id bigint not null,
        single_value_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        single_value_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_P_DF_2_ML_DEF (
        datasource_param_def_id bigint not null,
        mult_def_val_simpl_data_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_P_DF_2_ML_DEF_A (
        REV integer not null,
        datasource_param_def_id bigint not null,
        mult_def_val_simpl_data_id bigint not null,
        revtype tinyint,
        primary key (REV, datasource_param_def_id, mult_def_val_simpl_data_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_P_INS_2_ML_VAL (
        datasource_param_inst_id bigint not null,
        multi_value_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATASOURCE_P_INS_2_ML_VAL_A (
        REV integer not null,
        datasource_param_inst_id bigint not null,
        multi_value_id bigint not null,
        revtype tinyint,
        primary key (REV, datasource_param_inst_id, multi_value_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATETIME_PARAM_DEF (
        default_value datetime,
        formula varchar(255),
        MODE_FIELD integer,
        use_now_as_default BIT(1),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATETIME_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        default_value datetime,
        formula varchar(255),
        MODE_FIELD integer,
        use_now_as_default BIT(1),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATETIME_PARAM_INST (
        formula varchar(255),
        value datetime,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATETIME_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        formula varchar(255),
        value datetime,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DATE_TRIGGER_CONFIG (
        DTYPE varchar(31) not null,
        ENTITY_ID bigint not null auto_increment,
        at_time_hour integer,
        at_time_minutes integer,
        daily_repeat_type integer,
        end_type integer,
        first_execution datetime,
        last_execution datetime,
        number_of_executions integer,
        time_range_end_hour integer,
        time_range_end_minutes integer,
        time_range_interval integer,
        time_range_start_hour integer,
        time_range_start_minutes integer,
        time_range_unit integer,
        ENTITY_VERSION bigint,
        yearly_month integer,
        yearly_nd_ay integer,
        day_in_month integer,
        month integer,
        weeklyn integer,
        dailyn integer,
        pattern integer,
        yearly_day integer,
        yearly_nth integer,
        monthly_day integer,
        monthly_nth integer,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DB_BUNDLE_2_ENTRY (
        db_bundle_datasource_id bigint not null,
        bundle_entries_id bigint not null,
        primary key (db_bundle_datasource_id, bundle_entries_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DB_BUNDLE_2_ENTRY_A (
        REV integer not null,
        db_bundle_datasource_id bigint not null,
        bundle_entries_id bigint not null,
        revtype tinyint,
        primary key (REV, db_bundle_datasource_id, bundle_entries_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DB_BUNDLE_DATASOURCE (
        key_source varchar(255),
        key_source_param_name varchar(255),
        mapping_source varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_DB_BUNDLE_DATASOURCE_A (
        id bigint not null,
        REV integer not null,
        key_source varchar(255),
        key_source_param_name varchar(255),
        mapping_source varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_EXEC_REPORT_AS_FILE_REF (
        output_format varchar(255),
        id bigint not null,
        compiled_report_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_EXEC_REPORT_AS_FILE_REF_A (
        id bigint not null,
        REV integer not null,
        output_format varchar(255),
        compiled_report_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FAVORITE_LIST (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        user_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FAVORITE_LIST_2_ENTRY (
        favorite_list_id bigint not null,
        reference_entries_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FAVORITE_LIST_ENTRY (
        ENTITY_ID bigint not null auto_increment,
        position integer not null,
        ENTITY_VERSION bigint,
        reference_entry_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_DEF (
        allow_download BIT(1) not null,
        allow_file_server_selection BIT(1) not null,
        allow_file_upload BIT(1) not null,
        allow_team_space_selection BIT(1) not null,
        allowed_file_extensions varchar(255),
        file_size_string varchar(255),
        height integer not null,
        max_number_of_files integer,
        min_number_of_files integer,
        width integer not null,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        allow_download BIT(1),
        allow_file_server_selection BIT(1),
        allow_file_upload BIT(1),
        allow_team_space_selection BIT(1),
        allowed_file_extensions varchar(255),
        file_size_string varchar(255),
        height integer,
        max_number_of_files integer,
        min_number_of_files integer,
        width integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_INST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_IN_2_FILE (
        filesel_param_inst_id bigint not null,
        selected_files_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_IN_2_FILE_A (
        REV integer not null,
        filesel_param_inst_id bigint not null,
        selected_files_id bigint not null,
        revtype tinyint,
        primary key (REV, filesel_param_inst_id, selected_files_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_SEL_FILE (
        ENTITY_ID bigint not null auto_increment,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        file_server_file_id bigint,
        team_space_file_id bigint,
        uploaded_file_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_SEL_FILE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        NAME_FIELD varchar(128),
        file_server_file_id bigint,
        team_space_file_id bigint,
        uploaded_file_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_UP_FILE (
        ENTITY_ID bigint not null auto_increment,
        content longblob,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILESEL_PARAM_UP_FILE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content longblob,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_FILE (
        content_type varchar(128),
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        file_data_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_FILE_A (
        id bigint not null,
        REV integer not null,
        content_type varchar(128),
        description longtext,
        NAME_FIELD varchar(128),
        file_data_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_FILE_DATA (
        ENTITY_ID bigint not null auto_increment,
        data longblob,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_FILE_DATA_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        data longblob,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_FOLDER (
        description longtext,
        NAME_FIELD varchar(128),
        publicly_accessible BIT(1) not null,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_FOLDER_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        publicly_accessible BIT(1),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILE_SERVER_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER (
        ENTITY_ID bigint not null auto_increment,
        case_sensitive BIT(1),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_EXCLUDE_VAL (
        filter_id bigint not null,
        exclude_values varchar(255),
        val_n integer not null,
        primary key (filter_id, val_n)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_EXCLUDE_VAL_A (
        REV integer not null,
        filter_id bigint not null,
        exclude_values varchar(255) not null,
        val_n integer not null,
        revtype tinyint,
        primary key (REV, filter_id, exclude_values, val_n)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_FILTER_RNG_EXC (
        filter_id bigint not null,
        exclude_ranges_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_FILTER_RNG_EXC_A (
        REV integer not null,
        filter_id bigint not null,
        exclude_ranges_id bigint not null,
        revtype tinyint,
        primary key (REV, filter_id, exclude_ranges_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_FILTER_RNG_INC (
        filter_id bigint not null,
        include_ranges_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_FILTER_RNG_INC_A (
        REV integer not null,
        filter_id bigint not null,
        include_ranges_id bigint not null,
        revtype tinyint,
        primary key (REV, filter_id, include_ranges_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_INCLUDE_VAL (
        filter_id bigint not null,
        include_values varchar(255),
        val_n integer not null,
        primary key (filter_id, val_n)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_2_INCLUDE_VAL_A (
        REV integer not null,
        filter_id bigint not null,
        include_values varchar(255) not null,
        val_n integer not null,
        revtype tinyint,
        primary key (REV, filter_id, include_values, val_n)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        case_sensitive BIT(1),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_BLOCK (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_BLOCK_2_CHILD_BL (
        filter_block_id bigint not null,
        child_blocks_id bigint not null,
        primary key (filter_block_id, child_blocks_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_BLOCK_2_CHILD_BL_A (
        REV integer not null,
        filter_block_id bigint not null,
        child_blocks_id bigint not null,
        revtype tinyint,
        primary key (REV, filter_block_id, child_blocks_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_BLOCK_2_FILTERS (
        filter_block_id bigint not null,
        filters_id bigint not null,
        primary key (filter_block_id, filters_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_BLOCK_2_FILTERS_A (
        REV integer not null,
        filter_block_id bigint not null,
        filters_id bigint not null,
        revtype tinyint,
        primary key (REV, filter_block_id, filters_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_BLOCK_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_RANGE (
        ENTITY_ID bigint not null auto_increment,
        range_from varchar(255),
        range_to varchar(255),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_RANGE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        range_from varchar(255),
        range_to varchar(255),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_SPEC (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FILTER_SPEC_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FTP_DATASINK (
        folder varchar(1024),
        host varchar(1024),
        password varchar(255),
        port integer not null,
        username varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_FTP_DATASINK_A (
        id bigint not null,
        REV integer not null,
        folder varchar(1024),
        host varchar(1024),
        password varchar(255),
        port integer,
        username varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GEN_SECURITY_TGT_ENTITY (
        ENTITY_ID bigint not null auto_increment,
        target_identifier varchar(128),
        ENTITY_VERSION bigint,
        acl_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GEN_SECURITY_TGT_ENTITY_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        target_identifier varchar(128),
        acl_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GLOBAL_CONSTANT (
        ENTITY_ID bigint not null auto_increment,
        NAME_FIELD varchar(255),
        value varchar(255),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GLOBAL_CONSTANT_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        NAME_FIELD varchar(255),
        value varchar(255),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GRID_EDT_REPORT (
        arguments varchar(255),
        id bigint not null,
        script_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GRID_EDT_REPORT_A (
        id bigint not null,
        REV integer not null,
        arguments varchar(255),
        script_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GRID_EDT_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GRID_EDT_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP (
        description longtext,
        NAME_FIELD varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_2_GROUP (
        group_id bigint not null,
        referenced_groups_id bigint not null,
        primary key (group_id, referenced_groups_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_2_GROUP_A (
        REV integer not null,
        group_id bigint not null,
        referenced_groups_id bigint not null,
        revtype tinyint,
        primary key (REV, group_id, referenced_groups_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_2_OU (
        group_id bigint not null,
        ous_id bigint not null,
        primary key (group_id, ous_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_2_OU_A (
        REV integer not null,
        group_id bigint not null,
        ous_id bigint not null,
        revtype tinyint,
        primary key (REV, group_id, ous_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_2_USER (
        groups_id bigint not null,
        users_id bigint not null,
        primary key (groups_id, users_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_2_USER_A (
        REV integer not null,
        groups_id bigint not null,
        users_id bigint not null,
        revtype tinyint,
        primary key (REV, groups_id, users_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_GROUP_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HEADLINE_PARAM_DEF (
        value longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HEADLINE_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        value longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HEADLINE_PARAM_INST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HEADLINE_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HIERARCHICAL_ACE (
        inheritancetype integer not null,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HIERARCHICAL_ACE_A (
        id bigint not null,
        REV integer not null,
        inheritancetype integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HIERARCHICAL_ACL (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_HIERARCHICAL_ACL_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT (
        id bigint not null,
        master_file_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_2_SUB_JRXML (
        jasper_report_id bigint not null,
        sub_files_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_2_SUB_JRXML_A (
        REV integer not null,
        jasper_report_id bigint not null,
        sub_files_id bigint not null,
        revtype tinyint,
        primary key (REV, jasper_report_id, sub_files_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_A (
        id bigint not null,
        REV integer not null,
        master_file_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_JRXML (
        ENTITY_ID bigint not null auto_increment,
        content longtext,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_JRXML_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content longtext,
        NAME_FIELD varchar(128),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_TO_TABLE_CONFIG (
        id bigint not null,
        datasource_container_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JASPER_TO_TABLE_CONFIG_A (
        id bigint not null,
        REV integer not null,
        datasource_container_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JXLS_REPORT (
        jxls_one BIT(1) not null,
        id bigint not null,
        report_file_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JXLS_REPORT_A (
        id bigint not null,
        REV integer not null,
        jxls_one BIT(1),
        report_file_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JXLS_REPORT_FILE (
        ENTITY_ID bigint not null auto_increment,
        content longblob,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JXLS_REPORT_FILE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content longblob,
        NAME_FIELD varchar(128),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JXLS_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_JXLS_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_LIST_USERVARIABLE_DEF (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_LIST_USERVARIABLE_DEF_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_LIST_USERVARIABLE_INST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_LIST_USERVARIABLE_INST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_LIST_USERVARIABLE_INST_VL (
        list_user_var_instanc_id bigint not null,
        value varchar(255)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_LIST_USERVARIABLE_INST_VL_A (
        REV integer not null,
        list_user_var_instanc_id bigint not null,
        value varchar(255) not null,
        revtype tinyint,
        primary key (REV, list_user_var_instanc_id, value)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_MONDRIAN_DATASOURCE (
        mondrian3 BIT(1) not null,
        mondrian_schema longtext,
        password varchar(255),
        properties longtext,
        url varchar(255),
        username varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_MONDRIAN_DATASOURCE_A (
        id bigint not null,
        REV integer not null,
        mondrian3 BIT(1),
        mondrian_schema longtext,
        password varchar(255),
        properties longtext,
        url varchar(255),
        username varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_MONDRIAN_DATASOURCE_CFG (
        cube_name varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_MONDRIAN_DATASOURCE_CFG_A (
        id bigint not null,
        REV integer not null,
        cube_name varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ORGANISATIONAL_UNIT (
        description longtext,
        NAME_FIELD varchar(64),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_ORGANISATIONAL_UNIT_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(64),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PARAMETER_DEFINITION (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        display_inline BIT(1),
        editable BIT(1),
        hidden BIT(1),
        KEY_FIELD varchar(128),
        label_width integer,
        mandatory BIT(1) not null,
        n integer not null,
        NAME_FIELD varchar(255),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PARAMETER_DEFINITION_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        display_inline BIT(1),
        editable BIT(1),
        hidden BIT(1),
        KEY_FIELD varchar(128),
        label_width integer,
        mandatory BIT(1),
        n integer,
        NAME_FIELD varchar(255),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PARAMETER_INSTANCE (
        ENTITY_ID bigint not null auto_increment,
        still_default BIT(1) not null,
        ENTITY_VERSION bigint,
        definition_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PARAMETER_INSTANCE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        still_default BIT(1),
        definition_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PARAM_DEF_2_DEPENDANTS (
        parameter_definition_id bigint not null,
        depends_on_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PARAM_DEF_2_DEPENDANTS_A (
        REV integer not null,
        parameter_definition_id bigint not null,
        depends_on_id bigint not null,
        revtype tinyint,
        primary key (REV, parameter_definition_id, depends_on_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PRE_FILTER (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        root_block_type integer,
        ENTITY_VERSION bigint,
        root_block_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PRE_FILTER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        root_block_type integer,
        root_block_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(128) not null,
        value longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT (
        description longtext,
        KEY_FIELD varchar(40),
        NAME_FIELD varchar(128),
        uuid varchar(255),
        id bigint not null,
        datasource_container_id bigint,
        preview_image_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_METADATA (
        report_id bigint not null,
        report_metadata_id bigint not null,
        primary key (report_id, report_metadata_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_METADATA_A (
        REV integer not null,
        report_id bigint not null,
        report_metadata_id bigint not null,
        revtype tinyint,
        primary key (REV, report_id, report_metadata_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_PARAM_DEF (
        report_id bigint not null,
        parameter_definitions_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_PARAM_DEF_A (
        REV integer not null,
        report_id bigint not null,
        parameter_definitions_id bigint not null,
        revtype tinyint,
        primary key (REV, report_id, parameter_definitions_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_PARAM_INST (
        report_id bigint not null,
        parameter_instances_id bigint not null,
        primary key (report_id, parameter_instances_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_PARAM_INST_A (
        REV integer not null,
        report_id bigint not null,
        parameter_instances_id bigint not null,
        revtype tinyint,
        primary key (REV, report_id, parameter_instances_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_PROPERTY (
        report_id bigint not null,
        report_properties_id bigint not null,
        primary key (report_id, report_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_2_PROPERTY_A (
        REV integer not null,
        report_id bigint not null,
        report_properties_id bigint not null,
        revtype tinyint,
        primary key (REV, report_id, report_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        KEY_FIELD varchar(40),
        NAME_FIELD varchar(128),
        uuid varchar(255),
        datasource_container_id bigint,
        preview_image_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_BYTE_PROPERTY (
        byte_value longblob,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_BYTE_PROPERTY_A (
        id bigint not null,
        REV integer not null,
        byte_value longblob,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_FOLDER (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_FOLDER_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_METADATA (
        ENTITY_ID bigint not null auto_increment,
        NAME_FIELD varchar(40) not null,
        value longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_METADATA_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        NAME_FIELD varchar(40),
        value longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_MNGR_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_MNGR_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_PREVIEW_IMAGE (
        ENTITY_ID bigint not null auto_increment,
        content longblob,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_PREVIEW_IMAGE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content longblob,
        NAME_FIELD varchar(128),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        NAME_FIELD varchar(40) not null,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_PROPERTY_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        NAME_FIELD varchar(40),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_SS_STRING_PROPERTY (
        str_value longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_SS_STRING_PROPERTY_A (
        id bigint not null,
        REV integer not null,
        str_value longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_STRING_PROPERTY (
        str_value longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REPORT_STRING_PROPERTY_A (
        id bigint not null,
        REV integer not null,
        str_value longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_REVISION (
        ENTITY_ID integer not null auto_increment,
        timestamp bigint not null,
        user_id bigint not null,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SAIKU_REPORT (
        allow_mdx BIT(1) not null,
        hide_parents BIT(1) not null,
        query_xml longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SAIKU_REPORT_A (
        id bigint not null,
        REV integer not null,
        allow_mdx BIT(1),
        hide_parents BIT(1),
        query_xml longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SAIKU_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SAIKU_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHEDULER_JOB_HISTORY (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_AS_FILE (
        description varchar(255),
        folder_id bigint,
        NAME_FIELD varchar(255),
        teamspace_id bigint,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_AS_FTP_FILE (
        folder varchar(255),
        NAME_FIELD varchar(255),
        id bigint not null,
        ftp_datasink_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_AS_SFTP_FILE (
        folder varchar(255),
        NAME_FIELD varchar(255),
        id bigint not null,
        sftp_datasink_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_ENT_2_PROP (
        sched_hist_action_entry_id bigint not null,
        history_properties_id bigint not null,
        primary key (sched_hist_action_entry_id, history_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_MAIL_REPORT (
        compressed BIT(1) not null,
        message longtext,
        subject varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_SEND_TO (
        send_to_id varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_ACTION_SEND_TO_V (
        ENTITY_ID bigint not null auto_increment,
        the_value longtext,
        value_id varchar(255),
        ENTITY_VERSION bigint,
        SEND_TO bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_BASE_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(64) not null,
        value longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_EXECUTE_REPORT_JOB (
        output_format varchar(255),
        id bigint not null,
        executor_id bigint,
        scheduled_by_id bigint,
        report_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_EXECUTE_SCRIPT_JOB (
        arguments varchar(255),
        script_id bigint,
        id bigint not null,
        executor_id bigint,
        scheduled_by_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_HIST_ACTION_ENTRY (
        ENTITY_ID bigint not null auto_increment,
        action_name varchar(255),
        error_description longtext,
        outcome integer,
        ENTITY_VERSION bigint,
        EXEC_ENTRY bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_HIST_ENTRY_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(64) not null,
        value longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_HIST_EXEC_ENTRY (
        ENTITY_ID bigint not null auto_increment,
        bad_error_description longtext,
        END_FIELD datetime,
        outcome integer,
        scheduled_start datetime,
        START_FIELD datetime,
        ENTITY_VERSION bigint,
        veto_explanation longtext,
        veto_mode integer,
        job_entry_id bigint,
        JOB_HISTORY bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_HIST_JOB_ENTRY (
        ENTITY_ID bigint not null auto_increment,
        error_description longtext,
        outcome integer,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_JOB (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        description longtext,
        execution_status integer,
        last_outcome integer,
        title varchar(128),
        ENTITY_VERSION bigint,
        history_id bigint,
        link_to_previous_id bigint,
        trigger_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_JOB_2_ACTIONS (
        sched_job_id bigint not null,
        actions_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_JOB_2_OWNER (
        sched_execute_report_job_id bigint not null,
        owners_id bigint not null,
        primary key (sched_execute_report_job_id, owners_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_JOB_ENT_2_PROP (
        sched_hist_job_entry_id bigint not null,
        history_properties_id bigint not null,
        primary key (sched_hist_job_entry_id, history_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_REP_EXEC_JOB_2_PROP (
        sched_execute_report_job_id bigint not null,
        base_properties_id bigint not null,
        primary key (sched_execute_report_job_id, base_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_REP_EXEC_JOB_2_RCPT (
        report_execute_job_id bigint not null,
        rcpt_ids bigint
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_SCR_EXE_JOB_2_PROP (
        sched_execute_script_job_id bigint not null,
        base_properties_id bigint not null,
        primary key (sched_execute_script_job_id, base_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIGGER (
        ENTITY_ID bigint not null auto_increment,
        execute_once BIT(1) not null,
        first_fire_time datetime,
        misfire_instruction integer,
        next_scheduled_fire_time datetime,
        nr_of_failed_executions integer not null,
        nr_of_successful_executions integer not null,
        nr_of_vetoed_executions integer not null,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_DAILY_NTHDAY (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_DAILY_WORKDAY (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_DATE (
        id bigint not null,
        config_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_MONTH_NAMED_DAY (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_MON_DAY_O_MON (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_WEEKLY (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_YEARLY_AT_DATE (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCHED_TRIG_YEAR_NAMED_DAY (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_DATASOURCE (
        database_cache integer not null,
        define_at_target BIT(1) not null,
        id bigint not null,
        script_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_DATASOURCE_A (
        id bigint not null,
        REV integer not null,
        database_cache integer,
        define_at_target BIT(1),
        script_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_DATASOURCE_CONFIG (
        arguments varchar(255),
        query_wrapper varchar(4096),
        script longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_DATASOURCE_CONFIG_A (
        id bigint not null,
        REV integer not null,
        arguments varchar(255),
        query_wrapper varchar(4096),
        script longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_PARAM_DEF (
        arguments varchar(255),
        default_value longtext,
        height integer,
        width integer,
        id bigint not null,
        script_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        arguments varchar(255),
        default_value longtext,
        height integer,
        width integer,
        script_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_PARAM_INST (
        value longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        value longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_REPORT (
        arguments varchar(255),
        id bigint not null,
        script_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_REPORT_2_EX_FORMAT (
        script_report_id bigint not null,
        export_formats varchar(255),
        val_n integer not null,
        primary key (script_report_id, val_n)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_REPORT_2_EX_FORMAT_A (
        REV integer not null,
        script_report_id bigint not null,
        export_formats varchar(255) not null,
        val_n integer not null,
        revtype tinyint,
        primary key (REV, script_report_id, export_formats, val_n)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_REPORT_A (
        id bigint not null,
        REV integer not null,
        arguments varchar(255),
        script_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SCRIPT_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SEP_PARAM_DEF (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SEP_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SEP_PARAM_INST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SEP_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SFTP_DATASINK (
        folder varchar(1024),
        host varchar(1024),
        password varchar(255),
        port integer not null,
        username varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_SFTP_DATASINK_A (
        id bigint not null,
        REV integer not null,
        folder varchar(1024),
        host varchar(1024),
        password varchar(255),
        port integer,
        username varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_STR_USERVARIABLE_DEF (
        height integer,
        width integer,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_STR_USERVARIABLE_DEF_A (
        id bigint not null,
        REV integer not null,
        height integer,
        width integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_STR_USERVARIABLE_INST (
        value varchar(255),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_STR_USERVARIABLE_INST_A (
        id bigint not null,
        REV integer not null,
        value varchar(255),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT (
        allow_cubification BIT(1) not null,
        allow_mdx BIT(1) not null,
        cube_flag BIT(1) not null,
        cube_xml longtext,
        distinct_flag BIT(1),
        enable_subtotals BIT(1) not null,
        hide_parents BIT(1) not null,
        id bigint not null,
        metadata_datas_container_id bigint,
        pre_filter_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_2_ADD_COLUMN (
        table_report_id bigint not null,
        additional_columns_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_2_ADD_COLUMN_A (
        REV integer not null,
        table_report_id bigint not null,
        additional_columns_id bigint not null,
        revtype tinyint,
        primary key (REV, table_report_id, additional_columns_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_2_COLUMN (
        table_report_id bigint not null,
        columns_id bigint not null
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_2_COLUMN_A (
        REV integer not null,
        table_report_id bigint not null,
        columns_id bigint not null,
        revtype tinyint,
        primary key (REV, table_report_id, columns_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_A (
        id bigint not null,
        REV integer not null,
        allow_cubification BIT(1),
        allow_mdx BIT(1),
        cube_flag BIT(1),
        cube_xml longtext,
        distinct_flag BIT(1),
        enable_subtotals BIT(1),
        hide_parents BIT(1),
        metadata_datas_container_id bigint,
        pre_filter_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_BYTE_TPL (
        template longblob,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_BYTE_TPL_A (
        id bigint not null,
        REV integer not null,
        template longblob,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_STR_TEMPLATE (
        template longtext,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_STR_TEMPLATE_A (
        id bigint not null,
        REV integer not null,
        template longtext,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_TEMPLATE (
        ENTITY_ID bigint not null auto_increment,
        content_type varchar(255),
        description longtext,
        file_extension varchar(255),
        KEY_FIELD varchar(255),
        NAME_FIELD varchar(255),
        template_type varchar(255),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_TEMPLATE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        content_type varchar(255),
        description longtext,
        file_extension varchar(255),
        KEY_FIELD varchar(255),
        NAME_FIELD varchar(255),
        template_type varchar(255),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_TEMPLATE_LST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_TEMPLATE_LST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_VARIANT (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TABLE_REPORT_VARIANT_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TAB_REP_TPL_LST_2_TPL (
        table_report_templat_lst_id bigint not null,
        templates_id bigint not null,
        primary key (table_report_templat_lst_id, templates_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TAB_REP_TPL_LST_2_TPL_A (
        REV integer not null,
        table_report_templat_lst_id bigint not null,
        templates_id bigint not null,
        revtype tinyint,
        primary key (REV, table_report_templat_lst_id, templates_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        NAME_FIELD varchar(255),
        ENTITY_VERSION bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_2_APP (
        teamspace_id bigint not null,
        apps_id bigint not null,
        primary key (teamspace_id, apps_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_2_APP_A (
        REV integer not null,
        teamspace_id bigint not null,
        apps_id bigint not null,
        revtype tinyint,
        primary key (REV, teamspace_id, apps_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        NAME_FIELD varchar(255),
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_APP (
        ENTITY_ID bigint not null auto_increment,
        installed BIT(1),
        type varchar(32),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_APP_2_PROPERTY (
        teamspace_app_id bigint not null,
        app_properties_id bigint not null,
        primary key (teamspace_app_id, app_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_APP_2_PROPERTY_A (
        REV integer not null,
        teamspace_app_id bigint not null,
        app_properties_id bigint not null,
        revtype tinyint,
        primary key (REV, teamspace_app_id, app_properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_APP_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        installed BIT(1),
        type varchar(32),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_APP_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        NAME_FIELD varchar(32),
        value longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_APP_PROPERTY_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        NAME_FIELD varchar(32),
        value longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_MEMBER (
        ENTITY_ID bigint not null auto_increment,
        role integer,
        ENTITY_VERSION bigint,
        folk_id bigint,
        team_space_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEAMSPACE_MEMBER_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        role integer,
        folk_id bigint,
        team_space_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEXT_PARAM_DEF (
        default_value varchar(255),
        height integer,
        return_null_when_empty BIT(1) not null,
        return_type integer,
        validator_regex varchar(255),
        width integer,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEXT_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        default_value varchar(255),
        height integer,
        return_null_when_empty BIT(1),
        return_type integer,
        validator_regex varchar(255),
        width integer,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEXT_PARAM_INST (
        value varchar(4000),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TEXT_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        value varchar(4000),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_FOLDER (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_FOLDER_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_GENERAL_REFERENCE (
        description longtext,
        NAME_FIELD varchar(128) not null,
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_GENERAL_REFERENCE_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        parent_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        parent_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_REPORT_REFERENCE (
        hardlink BIT(1),
        id bigint not null,
        report_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_REPORT_REFERENCE_A (
        id bigint not null,
        REV integer not null,
        hardlink BIT(1),
        report_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_ROOT (
        description longtext,
        NAME_FIELD varchar(128),
        id bigint not null,
        team_space_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_TS_DISK_ROOT_A (
        id bigint not null,
        REV integer not null,
        description longtext,
        NAME_FIELD varchar(128),
        team_space_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USER (
        email varchar(320),
        firstname varchar(128),
        lastname varchar(128),
        password varchar(40),
        sex integer,
        super_user BIT(1),
        title varchar(40),
        username varchar(128),
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERMANAGER_NODE (
        ENTITY_ID bigint not null auto_increment,
        created_on datetime,
        flags bigint not null,
        last_updated datetime,
        position integer not null,
        ENTITY_VERSION bigint,
        guid varchar(128),
        origin varchar(1024),
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERMANAGER_NODE_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        created_on datetime,
        flags bigint,
        last_updated datetime,
        guid varchar(128),
        origin varchar(1024),
        parent_id bigint,
        acl_id bigint,
        owner_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_DEF (
        ENTITY_ID bigint not null auto_increment,
        description longtext,
        NAME_FIELD varchar(128),
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_DEF_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        description longtext,
        NAME_FIELD varchar(128),
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_INST (
        ENTITY_ID bigint not null auto_increment,
        ENTITY_VERSION bigint,
        definition_id bigint,
        folk_id bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_INST_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        definition_id bigint,
        folk_id bigint,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_PARAM_DEF (
        id bigint not null,
        user_variable_definition_id bigint,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_PARAM_DEF_A (
        id bigint not null,
        REV integer not null,
        user_variable_definition_id bigint,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_PARAM_INST (
        id bigint not null,
        primary key (id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USERVAR_PARAM_INST_A (
        id bigint not null,
        REV integer not null,
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USER_2_PROPERTY (
        user_id bigint not null,
        properties_id bigint not null,
        primary key (user_id, properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USER_2_PROPERTY_A (
        REV integer not null,
        user_id bigint not null,
        properties_id bigint not null,
        revtype tinyint,
        primary key (REV, user_id, properties_id)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USER_A (
        id bigint not null,
        REV integer not null,
        email varchar(320),
        firstname varchar(128),
        lastname varchar(128),
        password varchar(40),
        sex integer,
        super_user BIT(1),
        title varchar(40),
        username varchar(128),
        primary key (id, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USER_PROPERTY (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(64) not null,
        value longtext,
        ENTITY_VERSION bigint,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_USER_PROPERTY_A (
        ENTITY_ID bigint not null,
        REV integer not null,
        revtype tinyint,
        KEY_FIELD varchar(64),
        value longtext,
        primary key (ENTITY_ID, REV)
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    create table RS_WEEKLY_CONFIG_2_DAYS (
        weekly_config_id bigint not null,
        weekly_days integer
    ) CHARACTER SET utf8 COLLATE utf8_bin;

    alter table RS_ACE 
        add index FK_41b63u9jt26kws13tx2usblq0 (acl_id), 
        add constraint FK_41b63u9jt26kws13tx2usblq0 
        foreign key (acl_id) 
        references RS_ACL (ENTITY_ID);

    alter table RS_ACE 
        add index FK_ifosfssq8o25pr1l9pigp2ice (folk_id), 
        add constraint FK_ifosfssq8o25pr1l9pigp2ice 
        foreign key (folk_id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_ACE_2_ACCESS_MAPS 
        add constraint UK_27x19f34k1llernqtf8ltkfax unique (access_maps_id);

    alter table RS_ACE_2_ACCESS_MAPS 
        add index FK_27x19f34k1llernqtf8ltkfax (access_maps_id), 
        add constraint FK_27x19f34k1llernqtf8ltkfax 
        foreign key (access_maps_id) 
        references RS_ACE_ACCESS_MAP (ENTITY_ID);

    alter table RS_ACE_2_ACCESS_MAPS 
        add index FK_dkaj7gnkjjo1ey23tlowxcsov (ace_id), 
        add constraint FK_dkaj7gnkjjo1ey23tlowxcsov 
        foreign key (ace_id) 
        references RS_ACE (ENTITY_ID);

    alter table RS_ACE_2_ACCESS_MAPS_A 
        add index FK_ke0v25jpbae67u1auu9vo1uum (REV), 
        add constraint FK_ke0v25jpbae67u1auu9vo1uum 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_ACE_A 
        add index FK_kfc6e5xqh5x5pa2n9ajoh3m7m (REV), 
        add constraint FK_kfc6e5xqh5x5pa2n9ajoh3m7m 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_ACE_ACCESS_MAP_A 
        add index FK_ut7ypv66la6b5kw7yj72qxp5 (REV), 
        add constraint FK_ut7ypv66la6b5kw7yj72qxp5 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_ACL_A 
        add index FK_g1ig6c8pa6ht47mhefelyf8ww (REV), 
        add constraint FK_g1ig6c8pa6ht47mhefelyf8ww 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_ADD_COLUMN_SPEC 
        add index FK_7l3ysf1xd1yjbhevewgjuov3p (id), 
        add constraint FK_7l3ysf1xd1yjbhevewgjuov3p 
        foreign key (id) 
        references RS_COLUMN (ENTITY_ID);

    alter table RS_ADD_COLUMN_SPEC_A 
        add index FK_d3h61x0jj6odxbw47jokodxdg (id, REV), 
        add constraint FK_d3h61x0jj6odxbw47jokodxdg 
        foreign key (id, REV) 
        references RS_COLUMN_A (ENTITY_ID, REV);

    alter table RS_AUDIT_LOG_PROPERTY 
        add index FK_8e9xv91kk1t0ggo9ugi62mgh5 (LOG_ENTRY_ID), 
        add constraint FK_8e9xv91kk1t0ggo9ugi62mgh5 
        foreign key (LOG_ENTRY_ID) 
        references RS_AUDIT_LOG_ENTRY (ENTITY_ID);

    alter table RS_BINARY_COLUMN_FILTER 
        add index FK_dtfy09w92hpq6u65bjoxnscev (columna_id), 
        add constraint FK_dtfy09w92hpq6u65bjoxnscev 
        foreign key (columna_id) 
        references RS_COLUMN (ENTITY_ID);

    alter table RS_BINARY_COLUMN_FILTER 
        add index FK_ei2eql2bj4mffd7sai6v2l1hc (columnb_id), 
        add constraint FK_ei2eql2bj4mffd7sai6v2l1hc 
        foreign key (columnb_id) 
        references RS_COLUMN (ENTITY_ID);

    alter table RS_BINARY_COLUMN_FILTER 
        add index FK_qv86p0ket117fh3tofauyxugj (id), 
        add constraint FK_qv86p0ket117fh3tofauyxugj 
        foreign key (id) 
        references RS_FILTER_SPEC (ENTITY_ID);

    alter table RS_BINARY_COLUMN_FILTER_A 
        add index FK_skmlf8aydwmktxgn9nvrcle47 (id, REV), 
        add constraint FK_skmlf8aydwmktxgn9nvrcle47 
        foreign key (id, REV) 
        references RS_FILTER_SPEC_A (ENTITY_ID, REV);

    alter table RS_BIRT_REPORT 
        add index FK_4kfrhrtdarij5ufwxcwawb7o6 (report_file_id), 
        add constraint FK_4kfrhrtdarij5ufwxcwawb7o6 
        foreign key (report_file_id) 
        references RS_BIRT_REPORT_FILE (ENTITY_ID);

    alter table RS_BIRT_REPORT 
        add index FK_a3udh7dcfldqnjebwwiamlm1r (id), 
        add constraint FK_a3udh7dcfldqnjebwwiamlm1r 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_BIRT_REPORT_A 
        add index FK_jtbp7r1gtlpuxr79gnyei8gi4 (id, REV), 
        add constraint FK_jtbp7r1gtlpuxr79gnyei8gi4 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_BIRT_REPORT_DATASRC 
        add index FK_el88nw2w56ks3gndly5jlcyvw (id), 
        add constraint FK_el88nw2w56ks3gndly5jlcyvw 
        foreign key (id) 
        references RS_DATASOURCE_DEFINITION (id);

    alter table RS_BIRT_REPORT_DATASRC_A 
        add index FK_kelgsdp7dacr0inqwb74qgd22 (id, REV), 
        add constraint FK_kelgsdp7dacr0inqwb74qgd22 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEFINITION_A (id, REV);

    alter table RS_BIRT_REPORT_DATASRC_CFG 
        add index FK_n8kbf7s61t7ilf38w4rlvts6c (report_id), 
        add constraint FK_n8kbf7s61t7ilf38w4rlvts6c 
        foreign key (report_id) 
        references RS_BIRT_REPORT (id);

    alter table RS_BIRT_REPORT_DATASRC_CFG 
        add index FK_7x7pwf3w7cpwkya7dor7y41qj (id), 
        add constraint FK_7x7pwf3w7cpwkya7dor7y41qj 
        foreign key (id) 
        references RS_DATASOURCE_DEF_CONFIG (ENTITY_ID);

    alter table RS_BIRT_REPORT_DATASRC_CFG_A 
        add index FK_k4cq0niwexse6w20tq9rywytf (id, REV), 
        add constraint FK_k4cq0niwexse6w20tq9rywytf 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEF_CONFIG_A (ENTITY_ID, REV);

    alter table RS_BIRT_REPORT_FILE_A 
        add index FK_59cltbfssvceq1bakf896c05p (REV), 
        add constraint FK_59cltbfssvceq1bakf896c05p 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_BIRT_REPORT_VARIANT 
        add index FK_as4ju6u086juwxwqcr1ff8ipo (id), 
        add constraint FK_as4ju6u086juwxwqcr1ff8ipo 
        foreign key (id) 
        references RS_BIRT_REPORT (id);

    alter table RS_BIRT_REPORT_VARIANT_A 
        add index FK_1c5l1vgpvjdvyk5dwhfo6pg9e (id, REV), 
        add constraint FK_1c5l1vgpvjdvyk5dwhfo6pg9e 
        foreign key (id, REV) 
        references RS_BIRT_REPORT_A (id, REV);

    alter table RS_BLATEXT_PARAM_DEF 
        add index FK_omuk1k2yy30t608ynsfqn7egp (id), 
        add constraint FK_omuk1k2yy30t608ynsfqn7egp 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_BLATEXT_PARAM_DEF_A 
        add index FK_q9i8lkhblm0m98x974s3gvl1g (id, REV), 
        add constraint FK_q9i8lkhblm0m98x974s3gvl1g 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_BLATEXT_PARAM_INST 
        add index FK_1f9nfssiger42ep9i82v35n3q (id), 
        add constraint FK_1f9nfssiger42ep9i82v35n3q 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_BLATEXT_PARAM_INST_A 
        add index FK_1bpxqr8t7ufnjccmvu2qbd30p (id, REV), 
        add constraint FK_1bpxqr8t7ufnjccmvu2qbd30p 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_COLUMN 
        add index FK_9penq6b0e48w9huiubm93ekyu (filter_id), 
        add constraint FK_9penq6b0e48w9huiubm93ekyu 
        foreign key (filter_id) 
        references RS_FILTER (ENTITY_ID);

    alter table RS_COLUMN 
        add index FK_3ik9n12xyqgt09qoi3n1thc5 (format_id), 
        add constraint FK_3ik9n12xyqgt09qoi3n1thc5 
        foreign key (format_id) 
        references RS_COLUMN_FORMAT (ENTITY_ID);

    alter table RS_COLUMN_A 
        add index FK_vjsb21w9iscntp4imkvw12yt (REV), 
        add constraint FK_vjsb21w9iscntp4imkvw12yt 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_COLUMN_FILTER 
        add index FK_sm3ugqrfrocbk0wlmt9i4abps (column_id), 
        add constraint FK_sm3ugqrfrocbk0wlmt9i4abps 
        foreign key (column_id) 
        references RS_COLUMN (ENTITY_ID);

    alter table RS_COLUMN_FILTER 
        add index FK_7u1ox3javqc8cx6yp9aoa47kj (id), 
        add constraint FK_7u1ox3javqc8cx6yp9aoa47kj 
        foreign key (id) 
        references RS_FILTER_SPEC (ENTITY_ID);

    alter table RS_COLUMN_FILTER_A 
        add index FK_6q3vcgqulobaqgm40emc6xq75 (id, REV), 
        add constraint FK_6q3vcgqulobaqgm40emc6xq75 
        foreign key (id, REV) 
        references RS_FILTER_SPEC_A (ENTITY_ID, REV);

    alter table RS_COLUMN_FORMAT_A 
        add index FK_q7kvxplwqx81t5cfc00t65v9y (REV), 
        add constraint FK_q7kvxplwqx81t5cfc00t65v9y 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_COLUMN_FORMAT_CURRENCY 
        add index FK_tkbsw1juw63iig50bxm9m30xn (id), 
        add constraint FK_tkbsw1juw63iig50bxm9m30xn 
        foreign key (id) 
        references RS_COLUMN_FORMAT_NUMBER (id);

    alter table RS_COLUMN_FORMAT_CURRENCY_A 
        add index FK_7674tphgj4lnkjowj0j99l8tn (id, REV), 
        add constraint FK_7674tphgj4lnkjowj0j99l8tn 
        foreign key (id, REV) 
        references RS_COLUMN_FORMAT_NUMBER_A (id, REV);

    alter table RS_COLUMN_FORMAT_DATE 
        add index FK_9v5897xyi40ddx12ypwq4xuf5 (id), 
        add constraint FK_9v5897xyi40ddx12ypwq4xuf5 
        foreign key (id) 
        references RS_COLUMN_FORMAT (ENTITY_ID);

    alter table RS_COLUMN_FORMAT_DATE_A 
        add index FK_91tgvr3x9polec0you6nddojc (id, REV), 
        add constraint FK_91tgvr3x9polec0you6nddojc 
        foreign key (id, REV) 
        references RS_COLUMN_FORMAT_A (ENTITY_ID, REV);

    alter table RS_COLUMN_FORMAT_NUMBER 
        add index FK_6pjc1teyuqfogdfjw9rungum7 (id), 
        add constraint FK_6pjc1teyuqfogdfjw9rungum7 
        foreign key (id) 
        references RS_COLUMN_FORMAT (ENTITY_ID);

    alter table RS_COLUMN_FORMAT_NUMBER_A 
        add index FK_1c4i69xthrhklpcsvwnc9dvha (id, REV), 
        add constraint FK_1c4i69xthrhklpcsvwnc9dvha 
        foreign key (id, REV) 
        references RS_COLUMN_FORMAT_A (ENTITY_ID, REV);

    alter table RS_COLUMN_FORMAT_TEMPLATE 
        add index FK_o6ulj8cbap7kpu5ylkyygqwat (id), 
        add constraint FK_o6ulj8cbap7kpu5ylkyygqwat 
        foreign key (id) 
        references RS_COLUMN_FORMAT (ENTITY_ID);

    alter table RS_COLUMN_FORMAT_TEMPLATE_A 
        add index FK_prfdvu2sp73evjruirqs9fm60 (id, REV), 
        add constraint FK_prfdvu2sp73evjruirqs9fm60 
        foreign key (id, REV) 
        references RS_COLUMN_FORMAT_A (ENTITY_ID, REV);

    alter table RS_COLUMN_FORMAT_TEXT 
        add index FK_i5vvpvmq7r006an48lv0am9bn (id), 
        add constraint FK_i5vvpvmq7r006an48lv0am9bn 
        foreign key (id) 
        references RS_COLUMN_FORMAT (ENTITY_ID);

    alter table RS_COLUMN_FORMAT_TEXT_A 
        add index FK_irfuls4gustxm229qr9cwya10 (id, REV), 
        add constraint FK_irfuls4gustxm229qr9cwya10 
        foreign key (id, REV) 
        references RS_COLUMN_FORMAT_A (ENTITY_ID, REV);

    alter table RS_COLUMN_REFERENCE 
        add index FK_p4ke85xjpim0u4obkjl1lwkll (reference_id), 
        add constraint FK_p4ke85xjpim0u4obkjl1lwkll 
        foreign key (reference_id) 
        references RS_ADD_COLUMN_SPEC (id);

    alter table RS_COLUMN_REFERENCE 
        add index FK_6ufi45qsr9qaqpk1xeb9ixtbi (id), 
        add constraint FK_6ufi45qsr9qaqpk1xeb9ixtbi 
        foreign key (id) 
        references RS_COLUMN (ENTITY_ID);

    alter table RS_COLUMN_REFERENCE_A 
        add index FK_jgwp9dqdt1e09ho7skdr0l6yg (id, REV), 
        add constraint FK_jgwp9dqdt1e09ho7skdr0l6yg 
        foreign key (id, REV) 
        references RS_COLUMN_A (ENTITY_ID, REV);

    alter table RS_COMPILED_REPORT 
        add index FK_9m234rflvttdbukyrb9sdvotk (report_id), 
        add constraint FK_9m234rflvttdbukyrb9sdvotk 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_COMPUTED_COLUMN 
        add index FK_m95i94sv61o141cscv2ckwshh (id), 
        add constraint FK_m95i94sv61o141cscv2ckwshh 
        foreign key (id) 
        references RS_ADD_COLUMN_SPEC (id);

    alter table RS_COMPUTED_COLUMN_A 
        add index FK_5upmxwsopc1ctthyku3ouicdx (id, REV), 
        add constraint FK_5upmxwsopc1ctthyku3ouicdx 
        foreign key (id, REV) 
        references RS_ADD_COLUMN_SPEC_A (id, REV);

    alter table RS_CONDITION 
        add index FK_hktlyaaqlw8e0d2b3dm3i185n (report_id), 
        add constraint FK_hktlyaaqlw8e0d2b3dm3i185n 
        foreign key (report_id) 
        references RS_TABLE_REPORT (id);

    alter table RS_CRYSTAL_REPORT 
        add index FK_oigy4smejx6hdmdtd07f8o9xp (report_file_id), 
        add constraint FK_oigy4smejx6hdmdtd07f8o9xp 
        foreign key (report_file_id) 
        references RS_CRYSTAL_REPORT_FILE (ENTITY_ID);

    alter table RS_CRYSTAL_REPORT 
        add index FK_4lvccgt3mxr9dms6k55kojxdl (id), 
        add constraint FK_4lvccgt3mxr9dms6k55kojxdl 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_CRYSTAL_REPORT_A 
        add index FK_8aa29v2smd9ir3ogb0ypsagpg (id, REV), 
        add constraint FK_8aa29v2smd9ir3ogb0ypsagpg 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_CRYSTAL_REPORT_FILE_A 
        add index FK_du2x63noi6oj8butfnh0y75pm (REV), 
        add constraint FK_du2x63noi6oj8butfnh0y75pm 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_CRYSTAL_REPORT_VARIANT 
        add index FK_bwafn9rgn0qmx3w3htpcglyaa (id), 
        add constraint FK_bwafn9rgn0qmx3w3htpcglyaa 
        foreign key (id) 
        references RS_CRYSTAL_REPORT (id);

    alter table RS_CRYSTAL_REPORT_VARIANT_A 
        add index FK_66wfsa3ii79demq0f1asoc2ne (id, REV), 
        add constraint FK_66wfsa3ii79demq0f1asoc2ne 
        foreign key (id, REV) 
        references RS_CRYSTAL_REPORT_A (id, REV);

    alter table RS_CSV_DATASOURCE 
        add index FK_mklifk8yintl3foxcl4xlsqep (connector_id), 
        add constraint FK_mklifk8yintl3foxcl4xlsqep 
        foreign key (connector_id) 
        references RS_DATASOURCE_CONNECTOR (ENTITY_ID);

    alter table RS_CSV_DATASOURCE 
        add index FK_qo0hu66ew1f2qiu540n8cpai7 (id), 
        add constraint FK_qo0hu66ew1f2qiu540n8cpai7 
        foreign key (id) 
        references RS_DATASOURCE_DEFINITION (id);

    alter table RS_CSV_DATASOURCE_A 
        add index FK_g85vdpx9ed3ocdxfgem3dwh42 (id, REV), 
        add constraint FK_g85vdpx9ed3ocdxfgem3dwh42 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEFINITION_A (id, REV);

    alter table RS_CSV_DATASOURCE_CONF 
        add index FK_pi9vcfuuqpmyud3vx7ag1foem (id), 
        add constraint FK_pi9vcfuuqpmyud3vx7ag1foem 
        foreign key (id) 
        references RS_DATASOURCE_DEF_CONFIG (ENTITY_ID);

    alter table RS_CSV_DATASOURCE_CONF_A 
        add index FK_lqru3jh78fuwvtcu6ufwgea54 (id, REV), 
        add constraint FK_lqru3jh78fuwvtcu6ufwgea54 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEF_CONFIG_A (ENTITY_ID, REV);

    alter table RS_DADGET_A 
        add index FK_nxmyn1jg2vg0qj00sbg2onnm3 (REV), 
        add constraint FK_nxmyn1jg2vg0qj00sbg2onnm3 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DADGET_FAVORITE_LIST 
        add index FK_c5232qw4acelawul9jtjr1bl7 (id), 
        add constraint FK_c5232qw4acelawul9jtjr1bl7 
        foreign key (id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DADGET_LIBRARY 
        add index FK_2hn2gb334vfmtgefu8xvhpy81 (dadget_node_id), 
        add constraint FK_2hn2gb334vfmtgefu8xvhpy81 
        foreign key (dadget_node_id) 
        references RS_DASHBOARD_DADGET_NODE (id);

    alter table RS_DADGET_LIBRARY 
        add index FK_ajr1bedisx8a0y5qvq1n1w8qk (id), 
        add constraint FK_ajr1bedisx8a0y5qvq1n1w8qk 
        foreign key (id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DADGET_PARAMETER 
        add index FK_qsva85k8phltnnix43as3o74o (id), 
        add constraint FK_qsva85k8phltnnix43as3o74o 
        foreign key (id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DADGET_REPORT 
        add index FK_lpmcb0y12fqoo7n6n785silqa (report_id), 
        add constraint FK_lpmcb0y12fqoo7n6n785silqa 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_DADGET_REPORT 
        add index FK_6uo29kujagr0aitmmqcgkmkmd (report_reference_id), 
        add constraint FK_6uo29kujagr0aitmmqcgkmkmd 
        foreign key (report_reference_id) 
        references RS_TS_DISK_REPORT_REFERENCE (id);

    alter table RS_DADGET_REPORT 
        add index FK_986ty0k81tm0xybg4booc1g9 (id), 
        add constraint FK_986ty0k81tm0xybg4booc1g9 
        foreign key (id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DADGET_REPORT_2_PARAM_INST 
        add constraint UK_c6stvwayicc4nek33e6wh0wsx unique (parameter_instances_id);

    alter table RS_DADGET_REPORT_2_PARAM_INST 
        add index FK_c6stvwayicc4nek33e6wh0wsx (parameter_instances_id), 
        add constraint FK_c6stvwayicc4nek33e6wh0wsx 
        foreign key (parameter_instances_id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_DADGET_REPORT_2_PARAM_INST 
        add index FK_d2pctfqw9v1l0akpgmnjm6j8j (dadget_id), 
        add constraint FK_d2pctfqw9v1l0akpgmnjm6j8j 
        foreign key (dadget_id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DADGET_STATIC_HTML 
        add index FK_sy2y2oo5gf2vej5af8r6kb67u (id), 
        add constraint FK_sy2y2oo5gf2vej5af8r6kb67u 
        foreign key (id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DADGET_URL 
        add index FK_spkrld97y3cpkrifingp7mndw (id), 
        add constraint FK_spkrld97y3cpkrifingp7mndw 
        foreign key (id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DASHBOARD_2_DADGET 
        add constraint UK_j1ykoyjigv4t99m5qn517yt2i unique (dadgets_id);

    alter table RS_DASHBOARD_2_DADGET 
        add index FK_j1ykoyjigv4t99m5qn517yt2i (dadgets_id), 
        add constraint FK_j1ykoyjigv4t99m5qn517yt2i 
        foreign key (dadgets_id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DASHBOARD_2_DADGET 
        add index FK_jwaxih6o06si6j61y6lx4cpdd (dashboard_id), 
        add constraint FK_jwaxih6o06si6j61y6lx4cpdd 
        foreign key (dashboard_id) 
        references RS_DASHBOARD (ENTITY_ID);

    alter table RS_DASHBOARD_2_DADGET_A 
        add index FK_tocyf2uxedy8e6go5ay4okrkh (REV), 
        add constraint FK_tocyf2uxedy8e6go5ay4okrkh 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DASHBOARD_A 
        add index FK_bb4u8e7ivhpd4dl1y6ergy79u (REV), 
        add constraint FK_bb4u8e7ivhpd4dl1y6ergy79u 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DASHBOARD_CONTAINER_A 
        add index FK_hnaijyyglpxjf4sb5wp3i9jw7 (REV), 
        add constraint FK_hnaijyyglpxjf4sb5wp3i9jw7 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DASHBOARD_CONT_2_DASHBRD 
        add constraint UK_j6n97pbwk8b6x3eyc6kbrmerf unique (dashboards_id);

    alter table RS_DASHBOARD_CONT_2_DASHBRD 
        add index FK_j6n97pbwk8b6x3eyc6kbrmerf (dashboards_id), 
        add constraint FK_j6n97pbwk8b6x3eyc6kbrmerf 
        foreign key (dashboards_id) 
        references RS_DASHBOARD (ENTITY_ID);

    alter table RS_DASHBOARD_CONT_2_DASHBRD 
        add index FK_sh5uw4q0hgju6k0sqvr5jc2eo (dashboard_container_id), 
        add constraint FK_sh5uw4q0hgju6k0sqvr5jc2eo 
        foreign key (dashboard_container_id) 
        references RS_DASHBOARD_CONTAINER (ENTITY_ID);

    alter table RS_DASHBOARD_CONT_2_DASHBRD_A 
        add index FK_ex3dp2tjru18f40fepfl5s0me (REV), 
        add constraint FK_ex3dp2tjru18f40fepfl5s0me 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DASHBOARD_DADGET_NODE 
        add index FK_5p18wfl2t16hhliummbm7tg87 (dadget_id), 
        add constraint FK_5p18wfl2t16hhliummbm7tg87 
        foreign key (dadget_id) 
        references RS_DADGET (ENTITY_ID);

    alter table RS_DASHBOARD_DADGET_NODE 
        add index FK_3ere1rt141fgqodaplbd9te81 (id), 
        add constraint FK_3ere1rt141fgqodaplbd9te81 
        foreign key (id) 
        references RS_DASHBOARD_MNGR_NODE (ENTITY_ID);

    alter table RS_DASHBOARD_DADGET_NODE_A 
        add index FK_s4umlmqlb7rs2ky4ylhtexl1r (id, REV), 
        add constraint FK_s4umlmqlb7rs2ky4ylhtexl1r 
        foreign key (id, REV) 
        references RS_DASHBOARD_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DASHBOARD_DASHBOARD_NODE 
        add index FK_thu5yd9ox65pr8difavfgamhf (dashboard_id), 
        add constraint FK_thu5yd9ox65pr8difavfgamhf 
        foreign key (dashboard_id) 
        references RS_DASHBOARD (ENTITY_ID);

    alter table RS_DASHBOARD_DASHBOARD_NODE 
        add index FK_ggktm3w5j1b0xgc2batvmtxki (id), 
        add constraint FK_ggktm3w5j1b0xgc2batvmtxki 
        foreign key (id) 
        references RS_DASHBOARD_MNGR_NODE (ENTITY_ID);

    alter table RS_DASHBOARD_DASHBOARD_NODE_A 
        add index FK_6tl7pfewyb0aj7c8i7020r5iq (id, REV), 
        add constraint FK_6tl7pfewyb0aj7c8i7020r5iq 
        foreign key (id, REV) 
        references RS_DASHBOARD_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DASHBOARD_FOLDER 
        add index FK_m8diw4yk2d1q6d6w8e2onwswf (id), 
        add constraint FK_m8diw4yk2d1q6d6w8e2onwswf 
        foreign key (id) 
        references RS_DASHBOARD_MNGR_NODE (ENTITY_ID);

    alter table RS_DASHBOARD_FOLDER_A 
        add index FK_1ejs2p9qcjjvm6udpibfjq8c4 (id, REV), 
        add constraint FK_1ejs2p9qcjjvm6udpibfjq8c4 
        foreign key (id, REV) 
        references RS_DASHBOARD_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DASHBOARD_MNGR_NODE 
        add index FK_72me98u9es40dt79ya5yvy0ix (parent_id), 
        add constraint FK_72me98u9es40dt79ya5yvy0ix 
        foreign key (parent_id) 
        references RS_DASHBOARD_MNGR_NODE (ENTITY_ID);

    alter table RS_DASHBOARD_MNGR_NODE 
        add index FK_91s7mcoyya17ai9e80dau8vam (acl_id), 
        add constraint FK_91s7mcoyya17ai9e80dau8vam 
        foreign key (acl_id) 
        references RS_HIERARCHICAL_ACL (id);

    alter table RS_DASHBOARD_MNGR_NODE 
        add index FK_jfanvvv31omwycmeadobok4af (owner_id), 
        add constraint FK_jfanvvv31omwycmeadobok4af 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_DASHBOARD_MNGR_NODE_A 
        add index FK_cufca6d5byxjinfxw4033h48v (REV), 
        add constraint FK_cufca6d5byxjinfxw4033h48v 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DASHBOARD_REFERENCE 
        add index FK_n1197kpl1muemg3rb67wu4kjx (dashboard_node_id), 
        add constraint FK_n1197kpl1muemg3rb67wu4kjx 
        foreign key (dashboard_node_id) 
        references RS_DASHBOARD_DASHBOARD_NODE (id);

    alter table RS_DASHBOARD_REFERENCE 
        add index FK_qt5xh84ia136ytmbgl55llobw (id), 
        add constraint FK_qt5xh84ia136ytmbgl55llobw 
        foreign key (id) 
        references RS_DASHBOARD (ENTITY_ID);

    alter table RS_DASHBOARD_REFERENCE_A 
        add index FK_4sbtq3sbarqehh9li4va06s8f (id, REV), 
        add constraint FK_4sbtq3sbarqehh9li4va06s8f 
        foreign key (id, REV) 
        references RS_DASHBOARD_A (ENTITY_ID, REV);

    alter table RS_DASHBOARD_USER 
        add index FK_rhdnh0hqmv19gv75mwd3llwk3 (dashboard_container_id), 
        add constraint FK_rhdnh0hqmv19gv75mwd3llwk3 
        foreign key (dashboard_container_id) 
        references RS_DASHBOARD_CONTAINER (ENTITY_ID);

    alter table RS_DASHBOARD_USER 
        add index FK_237rnf9st00rhd6ehn9tvyvp7 (user_id), 
        add constraint FK_237rnf9st00rhd6ehn9tvyvp7 
        foreign key (user_id) 
        references RS_USER (id);

    alter table RS_DASHBOARD_USER_A 
        add index FK_rvmcdn7iehlijjd3cmw4y1fak (REV), 
        add constraint FK_rvmcdn7iehlijjd3cmw4y1fak 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATABASE_BUNDLE_ENTRY 
        add index FK_e0q3p4hrc9grqqykcie6l4fah (database_id), 
        add constraint FK_e0q3p4hrc9grqqykcie6l4fah 
        foreign key (database_id) 
        references RS_DATASOURCE_MNGR_NODE (ENTITY_ID);

    alter table RS_DATABASE_BUNDLE_ENTRY_A 
        add index FK_k6al43guocy7gktsrdd9yy7yd (REV), 
        add constraint FK_k6al43guocy7gktsrdd9yy7yd 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATABASE_DATASOURCE 
        add index FK_dusjl76766ywiovsoxwimvr2i (id), 
        add constraint FK_dusjl76766ywiovsoxwimvr2i 
        foreign key (id) 
        references RS_DATASOURCE_DEFINITION (id);

    alter table RS_DATABASE_DATASOURCE_A 
        add index FK_qsqkdo5brp7wfe13e8x08xcaj (id, REV), 
        add constraint FK_qsqkdo5brp7wfe13e8x08xcaj 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEFINITION_A (id, REV);

    alter table RS_DATABASE_DATASOURCE_CONF 
        add index FK_ghfg4bwelu2wyww59wuo69r0x (id), 
        add constraint FK_ghfg4bwelu2wyww59wuo69r0x 
        foreign key (id) 
        references RS_DATASOURCE_DEF_CONFIG (ENTITY_ID);

    alter table RS_DATABASE_DATASOURCE_CONF_A 
        add index FK_hvpe101gxbmrwblses7dn2th8 (id, REV), 
        add constraint FK_hvpe101gxbmrwblses7dn2th8 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEF_CONFIG_A (ENTITY_ID, REV);

    alter table RS_DATASINK_CONTAINER 
        add index FK_h1q3hjijxsegbhohixwftjfi5 (datasink_id), 
        add constraint FK_h1q3hjijxsegbhohixwftjfi5 
        foreign key (datasink_id) 
        references RS_DATASINK_DEFINITION (id);

    alter table RS_DATASINK_CONTAINER_A 
        add index FK_mhqsxu075wn6respsm99qw2lk (REV), 
        add constraint FK_mhqsxu075wn6respsm99qw2lk 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASINK_DEFINITION 
        add index FK_bgphjr2kigltjqhlhyj956elj (id), 
        add constraint FK_bgphjr2kigltjqhlhyj956elj 
        foreign key (id) 
        references RS_DATASINK_MNGR_NODE (ENTITY_ID);

    alter table RS_DATASINK_DEFINITION_A 
        add index FK_bvph1qubtighemnp0mh4cpwl1 (id, REV), 
        add constraint FK_bvph1qubtighemnp0mh4cpwl1 
        foreign key (id, REV) 
        references RS_DATASINK_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DATASINK_FOLDER 
        add index FK_1rip8ww9heqw5dudpiybgb0wp (id), 
        add constraint FK_1rip8ww9heqw5dudpiybgb0wp 
        foreign key (id) 
        references RS_DATASINK_MNGR_NODE (ENTITY_ID);

    alter table RS_DATASINK_FOLDER_A 
        add index FK_3dn98yvtjr1dmqxmhd98t5e8 (id, REV), 
        add constraint FK_3dn98yvtjr1dmqxmhd98t5e8 
        foreign key (id, REV) 
        references RS_DATASINK_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DATASINK_MNGR_NODE 
        add index FK_sd7t8lv8a1y582c6spg43nx7d (parent_id), 
        add constraint FK_sd7t8lv8a1y582c6spg43nx7d 
        foreign key (parent_id) 
        references RS_DATASINK_MNGR_NODE (ENTITY_ID);

    alter table RS_DATASINK_MNGR_NODE 
        add index FK_k8pfp4tcr0fv315jsyvlpb57d (acl_id), 
        add constraint FK_k8pfp4tcr0fv315jsyvlpb57d 
        foreign key (acl_id) 
        references RS_HIERARCHICAL_ACL (id);

    alter table RS_DATASINK_MNGR_NODE 
        add index FK_1i15rxobwxutotj2w2l61kxf3 (owner_id), 
        add constraint FK_1i15rxobwxutotj2w2l61kxf3 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_DATASINK_MNGR_NODE_A 
        add index FK_oi5x0w4i14nd62ueplonjkh1h (REV), 
        add constraint FK_oi5x0w4i14nd62ueplonjkh1h 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_CONNECTOR_A 
        add index FK_q43tide6rqo7hnhp8x7twvlgo (REV), 
        add constraint FK_q43tide6rqo7hnhp8x7twvlgo 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_CONNECTOR_CFG_A 
        add index FK_thxg5k512c1xv8t0otfhttcwa (REV), 
        add constraint FK_thxg5k512c1xv8t0otfhttcwa 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_CONTAINER 
        add index FK_3fwh0h5cdf6w8876g21a2mof6 (datasource_id), 
        add constraint FK_3fwh0h5cdf6w8876g21a2mof6 
        foreign key (datasource_id) 
        references RS_DATASOURCE_DEFINITION (id);

    alter table RS_DATASOURCE_CONTAINER 
        add index FK_lo7prj3u3r8h32xx2ruin19bs (datasource_config_id), 
        add constraint FK_lo7prj3u3r8h32xx2ruin19bs 
        foreign key (datasource_config_id) 
        references RS_DATASOURCE_DEF_CONFIG (ENTITY_ID);

    alter table RS_DATASOURCE_CONTAINER_A 
        add index FK_ba7i4i6y5xod7dw7masaaco6x (REV), 
        add constraint FK_ba7i4i6y5xod7dw7masaaco6x 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_DEFINITION 
        add index FK_6kr6l2v6battuke1ejpo3xyl8 (id), 
        add constraint FK_6kr6l2v6battuke1ejpo3xyl8 
        foreign key (id) 
        references RS_DATASOURCE_MNGR_NODE (ENTITY_ID);

    alter table RS_DATASOURCE_DEFINITION_A 
        add index FK_cx9y4ah6jos314c9bwf0r9gw0 (id, REV), 
        add constraint FK_cx9y4ah6jos314c9bwf0r9gw0 
        foreign key (id, REV) 
        references RS_DATASOURCE_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DATASOURCE_DEF_CONFIG_A 
        add index FK_erwbayyqiqn0d2786q505x3uo (REV), 
        add constraint FK_erwbayyqiqn0d2786q505x3uo 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_FBCFG_2_DSCC 
        add constraint UK_93yp8r6kafnjysvai5ufxo1t4 unique (connector_config_id);

    alter table RS_DATASOURCE_FBCFG_2_DSCC 
        add index FK_93yp8r6kafnjysvai5ufxo1t4 (connector_config_id), 
        add constraint FK_93yp8r6kafnjysvai5ufxo1t4 
        foreign key (connector_config_id) 
        references RS_DATASOURCE_CONNECTOR_CFG (ENTITY_ID);

    alter table RS_DATASOURCE_FBCFG_2_DSCC 
        add index FK_7q3butkojy6896pj186puxhw (csv_datasource_conf_id), 
        add constraint FK_7q3butkojy6896pj186puxhw 
        foreign key (csv_datasource_conf_id) 
        references RS_CSV_DATASOURCE_CONF (id);

    alter table RS_DATASOURCE_FOLDER 
        add index FK_2jads9mxc9hensvgehddm46at (id), 
        add constraint FK_2jads9mxc9hensvgehddm46at 
        foreign key (id) 
        references RS_DATASOURCE_MNGR_NODE (ENTITY_ID);

    alter table RS_DATASOURCE_FOLDER_A 
        add index FK_70fq4kxb7i0s8k3m3i601ovth (id, REV), 
        add constraint FK_70fq4kxb7i0s8k3m3i601ovth 
        foreign key (id, REV) 
        references RS_DATASOURCE_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_DATASOURCE_MNGR_NODE 
        add index FK_fsohe90l330tsd6oj8klyf9k7 (parent_id), 
        add constraint FK_fsohe90l330tsd6oj8klyf9k7 
        foreign key (parent_id) 
        references RS_DATASOURCE_MNGR_NODE (ENTITY_ID);

    alter table RS_DATASOURCE_MNGR_NODE 
        add index FK_t1qko7dcwa7uq2obkqgncbwe9 (acl_id), 
        add constraint FK_t1qko7dcwa7uq2obkqgncbwe9 
        foreign key (acl_id) 
        references RS_HIERARCHICAL_ACL (id);

    alter table RS_DATASOURCE_MNGR_NODE 
        add index FK_l6ef4ykkyx194mb9bhm107gj (owner_id), 
        add constraint FK_l6ef4ykkyx194mb9bhm107gj 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_DATASOURCE_MNGR_NODE_A 
        add index FK_5li7y1ok0w0yo3vx95we982go (REV), 
        add constraint FK_5li7y1ok0w0yo3vx95we982go 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_PARAMETER_DATA_A 
        add index FK_sr2we9au2t0o7gi7wxdnoa5w2 (REV), 
        add constraint FK_sr2we9au2t0o7gi7wxdnoa5w2 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_PARAM_DEF 
        add index FK_6yteoqwtsq6tt0fp8nfqj3lay (datasource_container_id), 
        add constraint FK_6yteoqwtsq6tt0fp8nfqj3lay 
        foreign key (datasource_container_id) 
        references RS_DATASOURCE_CONTAINER (ENTITY_ID);

    alter table RS_DATASOURCE_PARAM_DEF 
        add index FK_1vcu5d6wgck87ah9x18hstl2 (s_def_value_simpl_data_id), 
        add constraint FK_1vcu5d6wgck87ah9x18hstl2 
        foreign key (s_def_value_simpl_data_id) 
        references RS_DATASOURCE_PARAMETER_DATA (ENTITY_ID);

    alter table RS_DATASOURCE_PARAM_DEF 
        add index FK_1xow1w58ol876och9yaqasyns (id), 
        add constraint FK_1xow1w58ol876och9yaqasyns 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_DATASOURCE_PARAM_DEF_A 
        add index FK_bx47i6g43q89qpdnjo7jmt8o1 (id, REV), 
        add constraint FK_bx47i6g43q89qpdnjo7jmt8o1 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_DATASOURCE_PARAM_INST 
        add index FK_9m0hjcp7181evupnlu7ntydpn (single_value_id), 
        add constraint FK_9m0hjcp7181evupnlu7ntydpn 
        foreign key (single_value_id) 
        references RS_DATASOURCE_PARAMETER_DATA (ENTITY_ID);

    alter table RS_DATASOURCE_PARAM_INST 
        add index FK_t3erhqg1lkwtxwnwxb01bomk1 (id), 
        add constraint FK_t3erhqg1lkwtxwnwxb01bomk1 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_DATASOURCE_PARAM_INST_A 
        add index FK_iym2a45lyplkry165anvh0gpw (id, REV), 
        add constraint FK_iym2a45lyplkry165anvh0gpw 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_DATASOURCE_P_DF_2_ML_DEF 
        add constraint UK_aac4miqa09m9f3kejtpruuv7r unique (mult_def_val_simpl_data_id);

    alter table RS_DATASOURCE_P_DF_2_ML_DEF 
        add index FK_aac4miqa09m9f3kejtpruuv7r (mult_def_val_simpl_data_id), 
        add constraint FK_aac4miqa09m9f3kejtpruuv7r 
        foreign key (mult_def_val_simpl_data_id) 
        references RS_DATASOURCE_PARAMETER_DATA (ENTITY_ID);

    alter table RS_DATASOURCE_P_DF_2_ML_DEF 
        add index FK_tbkqj34fw257fefgitbkcjhgb (datasource_param_def_id), 
        add constraint FK_tbkqj34fw257fefgitbkcjhgb 
        foreign key (datasource_param_def_id) 
        references RS_DATASOURCE_PARAM_DEF (id);

    alter table RS_DATASOURCE_P_DF_2_ML_DEF_A 
        add index FK_fammeekos1d7kiejametxc48d (REV), 
        add constraint FK_fammeekos1d7kiejametxc48d 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATASOURCE_P_INS_2_ML_VAL 
        add constraint UK_a7emx7qi7ryboelxucb3o9opp unique (multi_value_id);

    alter table RS_DATASOURCE_P_INS_2_ML_VAL 
        add index FK_a7emx7qi7ryboelxucb3o9opp (multi_value_id), 
        add constraint FK_a7emx7qi7ryboelxucb3o9opp 
        foreign key (multi_value_id) 
        references RS_DATASOURCE_PARAMETER_DATA (ENTITY_ID);

    alter table RS_DATASOURCE_P_INS_2_ML_VAL 
        add index FK_fq41r09br2nr4189x66kgj2dh (datasource_param_inst_id), 
        add constraint FK_fq41r09br2nr4189x66kgj2dh 
        foreign key (datasource_param_inst_id) 
        references RS_DATASOURCE_PARAM_INST (id);

    alter table RS_DATASOURCE_P_INS_2_ML_VAL_A 
        add index FK_qxdq70gj4l180m1l66ddjusbg (REV), 
        add constraint FK_qxdq70gj4l180m1l66ddjusbg 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DATETIME_PARAM_DEF 
        add index FK_r728myt1k8c4xfvk450pjalbs (id), 
        add constraint FK_r728myt1k8c4xfvk450pjalbs 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_DATETIME_PARAM_DEF_A 
        add index FK_5b342bvvpd5ae94h73cpvsm0g (id, REV), 
        add constraint FK_5b342bvvpd5ae94h73cpvsm0g 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_DATETIME_PARAM_INST 
        add index FK_le0v9g99kf0hb7eoskqhkagmm (id), 
        add constraint FK_le0v9g99kf0hb7eoskqhkagmm 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_DATETIME_PARAM_INST_A 
        add index FK_hxylsax84tdjyfdeh2h0qu7k5 (id, REV), 
        add constraint FK_hxylsax84tdjyfdeh2h0qu7k5 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_DB_BUNDLE_2_ENTRY 
        add constraint UK_eokthbj8u5gc495gvo7jb9q3v unique (bundle_entries_id);

    alter table RS_DB_BUNDLE_2_ENTRY 
        add index FK_eokthbj8u5gc495gvo7jb9q3v (bundle_entries_id), 
        add constraint FK_eokthbj8u5gc495gvo7jb9q3v 
        foreign key (bundle_entries_id) 
        references RS_DATABASE_BUNDLE_ENTRY (ENTITY_ID);

    alter table RS_DB_BUNDLE_2_ENTRY 
        add index FK_1m26a3pbc4y5ay16dk6ukq1ux (db_bundle_datasource_id), 
        add constraint FK_1m26a3pbc4y5ay16dk6ukq1ux 
        foreign key (db_bundle_datasource_id) 
        references RS_DB_BUNDLE_DATASOURCE (id);

    alter table RS_DB_BUNDLE_2_ENTRY_A 
        add index FK_lc8eov4scdkj464sgnuttdiws (REV), 
        add constraint FK_lc8eov4scdkj464sgnuttdiws 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_DB_BUNDLE_DATASOURCE 
        add index FK_ewaknfvj1ovob7twde2055w2m (id), 
        add constraint FK_ewaknfvj1ovob7twde2055w2m 
        foreign key (id) 
        references RS_DATABASE_DATASOURCE (id);

    alter table RS_DB_BUNDLE_DATASOURCE_A 
        add index FK_cgjqqnqn2d2t1660s7ibwfhlc (id, REV), 
        add constraint FK_cgjqqnqn2d2t1660s7ibwfhlc 
        foreign key (id, REV) 
        references RS_DATABASE_DATASOURCE_A (id, REV);

    alter table RS_EXEC_REPORT_AS_FILE_REF 
        add index FK_gulivudewy37o651onwppy6ev (compiled_report_id), 
        add constraint FK_gulivudewy37o651onwppy6ev 
        foreign key (compiled_report_id) 
        references RS_COMPILED_REPORT (ENTITY_ID);

    alter table RS_EXEC_REPORT_AS_FILE_REF 
        add index FK_5irwipw7c034ko8y7smbyb8n8 (id), 
        add constraint FK_5irwipw7c034ko8y7smbyb8n8 
        foreign key (id) 
        references RS_TS_DISK_GENERAL_REFERENCE (id);

    alter table RS_EXEC_REPORT_AS_FILE_REF_A 
        add index FK_nycy5dxd260mno8sbb6xodhdn (id, REV), 
        add constraint FK_nycy5dxd260mno8sbb6xodhdn 
        foreign key (id, REV) 
        references RS_TS_DISK_GENERAL_REFERENCE_A (id, REV);

    alter table RS_FAVORITE_LIST 
        add index FK_1be2jre7ckkkf12dokmbj080v (user_id), 
        add constraint FK_1be2jre7ckkkf12dokmbj080v 
        foreign key (user_id) 
        references RS_USER (id);

    alter table RS_FAVORITE_LIST_2_ENTRY 
        add constraint UK_7vmj24ydud0djkton4n1prlov unique (reference_entries_id);

    alter table RS_FAVORITE_LIST_2_ENTRY 
        add index FK_7vmj24ydud0djkton4n1prlov (reference_entries_id), 
        add constraint FK_7vmj24ydud0djkton4n1prlov 
        foreign key (reference_entries_id) 
        references RS_FAVORITE_LIST_ENTRY (ENTITY_ID);

    alter table RS_FAVORITE_LIST_2_ENTRY 
        add index FK_ibdq0e6t433ep7vwha1c1ll1k (favorite_list_id), 
        add constraint FK_ibdq0e6t433ep7vwha1c1ll1k 
        foreign key (favorite_list_id) 
        references RS_FAVORITE_LIST (ENTITY_ID);

    alter table RS_FAVORITE_LIST_ENTRY 
        add index FK_4rso5fo8fa5wwy6g426xxo608 (reference_entry_id), 
        add constraint FK_4rso5fo8fa5wwy6g426xxo608 
        foreign key (reference_entry_id) 
        references RS_TS_DISK_NODE (ENTITY_ID);

    alter table RS_FILESEL_PARAM_DEF 
        add index FK_3l4p3894c45y9xltx9ab90sdi (id), 
        add constraint FK_3l4p3894c45y9xltx9ab90sdi 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_FILESEL_PARAM_DEF_A 
        add index FK_lmva73ojyr6382etim98tf483 (id, REV), 
        add constraint FK_lmva73ojyr6382etim98tf483 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_FILESEL_PARAM_INST 
        add index FK_2wk2wx0w9uel2obef8ygbpabq (id), 
        add constraint FK_2wk2wx0w9uel2obef8ygbpabq 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_FILESEL_PARAM_INST_A 
        add index FK_o9a5c6w279fuv4qrbngqtny67 (id, REV), 
        add constraint FK_o9a5c6w279fuv4qrbngqtny67 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_FILESEL_PARAM_IN_2_FILE 
        add constraint UK_2hc95mx4mldfi0yh14qjaeqg7 unique (selected_files_id);

    alter table RS_FILESEL_PARAM_IN_2_FILE 
        add index FK_2hc95mx4mldfi0yh14qjaeqg7 (selected_files_id), 
        add constraint FK_2hc95mx4mldfi0yh14qjaeqg7 
        foreign key (selected_files_id) 
        references RS_FILESEL_PARAM_SEL_FILE (ENTITY_ID);

    alter table RS_FILESEL_PARAM_IN_2_FILE 
        add index FK_7awsqg8kiq51tjf3v514ibvam (filesel_param_inst_id), 
        add constraint FK_7awsqg8kiq51tjf3v514ibvam 
        foreign key (filesel_param_inst_id) 
        references RS_FILESEL_PARAM_INST (id);

    alter table RS_FILESEL_PARAM_IN_2_FILE_A 
        add index FK_okvonu2luqoc0ss9ha16m95xv (REV), 
        add constraint FK_okvonu2luqoc0ss9ha16m95xv 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILESEL_PARAM_SEL_FILE 
        add index FK_ri19q6nrg0gb9hccoi3nfuy64 (file_server_file_id), 
        add constraint FK_ri19q6nrg0gb9hccoi3nfuy64 
        foreign key (file_server_file_id) 
        references RS_FILE_SERVER_NODE (ENTITY_ID);

    alter table RS_FILESEL_PARAM_SEL_FILE 
        add index FK_iv3g1uxplrwhekkvph8jbawda (team_space_file_id), 
        add constraint FK_iv3g1uxplrwhekkvph8jbawda 
        foreign key (team_space_file_id) 
        references RS_TS_DISK_NODE (ENTITY_ID);

    alter table RS_FILESEL_PARAM_SEL_FILE 
        add index FK_r8scitcaed4eqypvukbx01an2 (uploaded_file_id), 
        add constraint FK_r8scitcaed4eqypvukbx01an2 
        foreign key (uploaded_file_id) 
        references RS_FILESEL_PARAM_UP_FILE (ENTITY_ID);

    alter table RS_FILESEL_PARAM_SEL_FILE_A 
        add index FK_62goa6338cij7hnrgnt22qmkc (REV), 
        add constraint FK_62goa6338cij7hnrgnt22qmkc 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILESEL_PARAM_UP_FILE_A 
        add index FK_ftpta70s1ukrtou0xqrogj0ug (REV), 
        add constraint FK_ftpta70s1ukrtou0xqrogj0ug 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILE_SERVER_FILE 
        add index FK_s8naupi8by11nxt2j1egtlpyk (file_data_id), 
        add constraint FK_s8naupi8by11nxt2j1egtlpyk 
        foreign key (file_data_id) 
        references RS_FILE_SERVER_FILE_DATA (ENTITY_ID);

    alter table RS_FILE_SERVER_FILE 
        add index FK_j14th63muv5rwfqgpr2q242rf (id), 
        add constraint FK_j14th63muv5rwfqgpr2q242rf 
        foreign key (id) 
        references RS_FILE_SERVER_NODE (ENTITY_ID);

    alter table RS_FILE_SERVER_FILE_A 
        add index FK_m412qpeipol5qyjl3h3m9pp9t (id, REV), 
        add constraint FK_m412qpeipol5qyjl3h3m9pp9t 
        foreign key (id, REV) 
        references RS_FILE_SERVER_NODE_A (ENTITY_ID, REV);

    alter table RS_FILE_SERVER_FILE_DATA_A 
        add index FK_4q66wssrns8qqya5syylj7glh (REV), 
        add constraint FK_4q66wssrns8qqya5syylj7glh 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILE_SERVER_FOLDER 
        add index FK_5fc1dof25ui9vk3cx8tdfvplq (id), 
        add constraint FK_5fc1dof25ui9vk3cx8tdfvplq 
        foreign key (id) 
        references RS_FILE_SERVER_NODE (ENTITY_ID);

    alter table RS_FILE_SERVER_FOLDER_A 
        add index FK_wmjc7mnh6kv9l9jodrsi157r (id, REV), 
        add constraint FK_wmjc7mnh6kv9l9jodrsi157r 
        foreign key (id, REV) 
        references RS_FILE_SERVER_NODE_A (ENTITY_ID, REV);

    alter table RS_FILE_SERVER_NODE 
        add index FK_mj5besgjxony0xbp3tuu1mo36 (parent_id), 
        add constraint FK_mj5besgjxony0xbp3tuu1mo36 
        foreign key (parent_id) 
        references RS_FILE_SERVER_NODE (ENTITY_ID);

    alter table RS_FILE_SERVER_NODE 
        add index FK_o1w4s4xntn0rcg7na1getwkm6 (acl_id), 
        add constraint FK_o1w4s4xntn0rcg7na1getwkm6 
        foreign key (acl_id) 
        references RS_HIERARCHICAL_ACL (id);

    alter table RS_FILE_SERVER_NODE 
        add index FK_pbg1cbpqtjcdispauvyd30k54 (owner_id), 
        add constraint FK_pbg1cbpqtjcdispauvyd30k54 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_FILE_SERVER_NODE_A 
        add index FK_5l0y0bmuteqvypsf1fj2oia3k (REV), 
        add constraint FK_5l0y0bmuteqvypsf1fj2oia3k 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_2_EXCLUDE_VAL 
        add index FK_43ql913dkg05u90bphbogqcvn (filter_id), 
        add constraint FK_43ql913dkg05u90bphbogqcvn 
        foreign key (filter_id) 
        references RS_FILTER (ENTITY_ID);

    alter table RS_FILTER_2_EXCLUDE_VAL_A 
        add index FK_1d8opconp6qgvroay3tqwiayc (REV), 
        add constraint FK_1d8opconp6qgvroay3tqwiayc 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_2_FILTER_RNG_EXC 
        add constraint UK_jsdutm04dlpvqsbgt9dftywlx unique (exclude_ranges_id);

    alter table RS_FILTER_2_FILTER_RNG_EXC 
        add index FK_jsdutm04dlpvqsbgt9dftywlx (exclude_ranges_id), 
        add constraint FK_jsdutm04dlpvqsbgt9dftywlx 
        foreign key (exclude_ranges_id) 
        references RS_FILTER_RANGE (ENTITY_ID);

    alter table RS_FILTER_2_FILTER_RNG_EXC 
        add index FK_hd0yfly9gvj1lyeb05tj5bhy5 (filter_id), 
        add constraint FK_hd0yfly9gvj1lyeb05tj5bhy5 
        foreign key (filter_id) 
        references RS_FILTER (ENTITY_ID);

    alter table RS_FILTER_2_FILTER_RNG_EXC_A 
        add index FK_hsombh60o8kl2esq5vs5numna (REV), 
        add constraint FK_hsombh60o8kl2esq5vs5numna 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_2_FILTER_RNG_INC 
        add constraint UK_khcr1at7ubxcehels6r1iij1b unique (include_ranges_id);

    alter table RS_FILTER_2_FILTER_RNG_INC 
        add index FK_khcr1at7ubxcehels6r1iij1b (include_ranges_id), 
        add constraint FK_khcr1at7ubxcehels6r1iij1b 
        foreign key (include_ranges_id) 
        references RS_FILTER_RANGE (ENTITY_ID);

    alter table RS_FILTER_2_FILTER_RNG_INC 
        add index FK_gf0ue1wo0soboclr9a1jc8nu6 (filter_id), 
        add constraint FK_gf0ue1wo0soboclr9a1jc8nu6 
        foreign key (filter_id) 
        references RS_FILTER (ENTITY_ID);

    alter table RS_FILTER_2_FILTER_RNG_INC_A 
        add index FK_pfolghm8dih21io802v1h2cox (REV), 
        add constraint FK_pfolghm8dih21io802v1h2cox 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_2_INCLUDE_VAL 
        add index FK_gcm2wc5mfpkuso1hiau9hphc0 (filter_id), 
        add constraint FK_gcm2wc5mfpkuso1hiau9hphc0 
        foreign key (filter_id) 
        references RS_FILTER (ENTITY_ID);

    alter table RS_FILTER_2_INCLUDE_VAL_A 
        add index FK_5uddq09uocdhfx3489h6i40ah (REV), 
        add constraint FK_5uddq09uocdhfx3489h6i40ah 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_A 
        add index FK_aohrftpkvp227xrdx1hkul9bg (REV), 
        add constraint FK_aohrftpkvp227xrdx1hkul9bg 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_BLOCK_2_CHILD_BL 
        add constraint UK_tqw8eh28g2w9mynsn76u84jnj unique (child_blocks_id);

    alter table RS_FILTER_BLOCK_2_CHILD_BL 
        add index FK_tqw8eh28g2w9mynsn76u84jnj (child_blocks_id), 
        add constraint FK_tqw8eh28g2w9mynsn76u84jnj 
        foreign key (child_blocks_id) 
        references RS_FILTER_BLOCK (ENTITY_ID);

    alter table RS_FILTER_BLOCK_2_CHILD_BL 
        add index FK_iybc1pfmp2d06upj8d02rx98y (filter_block_id), 
        add constraint FK_iybc1pfmp2d06upj8d02rx98y 
        foreign key (filter_block_id) 
        references RS_FILTER_BLOCK (ENTITY_ID);

    alter table RS_FILTER_BLOCK_2_CHILD_BL_A 
        add index FK_43x3sw7ctfsobr2v3ptfgp5ei (REV), 
        add constraint FK_43x3sw7ctfsobr2v3ptfgp5ei 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_BLOCK_2_FILTERS 
        add constraint UK_6fs79ndjctv8s6l8d8ugcyjpl unique (filters_id);

    alter table RS_FILTER_BLOCK_2_FILTERS 
        add index FK_6fs79ndjctv8s6l8d8ugcyjpl (filters_id), 
        add constraint FK_6fs79ndjctv8s6l8d8ugcyjpl 
        foreign key (filters_id) 
        references RS_FILTER_SPEC (ENTITY_ID);

    alter table RS_FILTER_BLOCK_2_FILTERS 
        add index FK_n7mnv0nd84ank1kgxnnfkamls (filter_block_id), 
        add constraint FK_n7mnv0nd84ank1kgxnnfkamls 
        foreign key (filter_block_id) 
        references RS_FILTER_BLOCK (ENTITY_ID);

    alter table RS_FILTER_BLOCK_2_FILTERS_A 
        add index FK_im498nadi89ith60nxig291wr (REV), 
        add constraint FK_im498nadi89ith60nxig291wr 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_BLOCK_A 
        add index FK_6qttm7wxe9xvp3tb9k434w48 (REV), 
        add constraint FK_6qttm7wxe9xvp3tb9k434w48 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_RANGE_A 
        add index FK_fo58lercm9j30p0danqqprjt1 (REV), 
        add constraint FK_fo58lercm9j30p0danqqprjt1 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FILTER_SPEC_A 
        add index FK_qybc3epbcimwvelvwf2av5usl (REV), 
        add constraint FK_qybc3epbcimwvelvwf2av5usl 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_FTP_DATASINK 
        add index FK_kveuhnqco33ihxub8f2lwnmhn (id), 
        add constraint FK_kveuhnqco33ihxub8f2lwnmhn 
        foreign key (id) 
        references RS_DATASINK_DEFINITION (id);

    alter table RS_FTP_DATASINK_A 
        add index FK_q65fbnctmy3tf5842e9robvkt (id, REV), 
        add constraint FK_q65fbnctmy3tf5842e9robvkt 
        foreign key (id, REV) 
        references RS_DATASINK_DEFINITION_A (id, REV);

    alter table RS_GEN_SECURITY_TGT_ENTITY 
        add constraint UK_7ado28p00lsx8v0m5l4iqypf5 unique (target_identifier);

    alter table RS_GEN_SECURITY_TGT_ENTITY 
        add index FK_6g2j9qplkr51t1ikiqrer55uj (acl_id), 
        add constraint FK_6g2j9qplkr51t1ikiqrer55uj 
        foreign key (acl_id) 
        references RS_ACL (ENTITY_ID);

    alter table RS_GEN_SECURITY_TGT_ENTITY_A 
        add index FK_f6lm5ju84cw8vf09q994g34i6 (REV), 
        add constraint FK_f6lm5ju84cw8vf09q994g34i6 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_GLOBAL_CONSTANT_A 
        add index FK_8fug0i9mif04uhv4ftcf4ipim (REV), 
        add constraint FK_8fug0i9mif04uhv4ftcf4ipim 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_GRID_EDT_REPORT 
        add index FK_1atoumh9rfbprvj4niwr25tm (script_id), 
        add constraint FK_1atoumh9rfbprvj4niwr25tm 
        foreign key (script_id) 
        references RS_FILE_SERVER_FILE (id);

    alter table RS_GRID_EDT_REPORT 
        add index FK_nvulej8ml7emgu5s17iu9q4qq (id), 
        add constraint FK_nvulej8ml7emgu5s17iu9q4qq 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_GRID_EDT_REPORT_A 
        add index FK_37iochesaqcqmhj52nchv4y85 (id, REV), 
        add constraint FK_37iochesaqcqmhj52nchv4y85 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_GRID_EDT_REPORT_VARIANT 
        add index FK_i5gapjl4xwqrms81t1q6468l6 (id), 
        add constraint FK_i5gapjl4xwqrms81t1q6468l6 
        foreign key (id) 
        references RS_GRID_EDT_REPORT (id);

    alter table RS_GRID_EDT_REPORT_VARIANT_A 
        add index FK_ncjwa01bn9rwewpklx7rsa2uy (id, REV), 
        add constraint FK_ncjwa01bn9rwewpklx7rsa2uy 
        foreign key (id, REV) 
        references RS_GRID_EDT_REPORT_A (id, REV);

    alter table RS_GROUP 
        add index FK_jajjdsyr24isb0qowritstva (id), 
        add constraint FK_jajjdsyr24isb0qowritstva 
        foreign key (id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_GROUP_2_GROUP 
        add index FK_3m64rqs20qfui6lf3sdvmxsly (referenced_groups_id), 
        add constraint FK_3m64rqs20qfui6lf3sdvmxsly 
        foreign key (referenced_groups_id) 
        references RS_GROUP (id);

    alter table RS_GROUP_2_GROUP 
        add index FK_fh2hithtcyv3m1lfr81g4kys1 (group_id), 
        add constraint FK_fh2hithtcyv3m1lfr81g4kys1 
        foreign key (group_id) 
        references RS_GROUP (id);

    alter table RS_GROUP_2_GROUP_A 
        add index FK_s027iw41cpxb7tg73aafwx69o (REV), 
        add constraint FK_s027iw41cpxb7tg73aafwx69o 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_GROUP_2_OU 
        add index FK_570w0rit5a0no3p09mjqi2sac (ous_id), 
        add constraint FK_570w0rit5a0no3p09mjqi2sac 
        foreign key (ous_id) 
        references RS_ORGANISATIONAL_UNIT (id);

    alter table RS_GROUP_2_OU 
        add index FK_c5rgyfh7ge4imi9sjocd2mvfx (group_id), 
        add constraint FK_c5rgyfh7ge4imi9sjocd2mvfx 
        foreign key (group_id) 
        references RS_GROUP (id);

    alter table RS_GROUP_2_OU_A 
        add index FK_1lcwmbfiqcap4g9kckmpfw1v5 (REV), 
        add constraint FK_1lcwmbfiqcap4g9kckmpfw1v5 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_GROUP_2_USER 
        add index FK_tlkylktyu8gy7mutoo2iasbah (users_id), 
        add constraint FK_tlkylktyu8gy7mutoo2iasbah 
        foreign key (users_id) 
        references RS_USER (id);

    alter table RS_GROUP_2_USER 
        add index FK_g51sc6mc56ekfon353g7ms0ns (groups_id), 
        add constraint FK_g51sc6mc56ekfon353g7ms0ns 
        foreign key (groups_id) 
        references RS_GROUP (id);

    alter table RS_GROUP_2_USER_A 
        add index FK_hun613qbdf5sbvkqfggmasrie (REV), 
        add constraint FK_hun613qbdf5sbvkqfggmasrie 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_GROUP_A 
        add index FK_5emgeivgm7y3dtxbvs1bw1lun (id, REV), 
        add constraint FK_5emgeivgm7y3dtxbvs1bw1lun 
        foreign key (id, REV) 
        references RS_USERMANAGER_NODE_A (ENTITY_ID, REV);

    alter table RS_HEADLINE_PARAM_DEF 
        add index FK_p8vb3wlamlo68h9p3x6mev0ch (id), 
        add constraint FK_p8vb3wlamlo68h9p3x6mev0ch 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_HEADLINE_PARAM_DEF_A 
        add index FK_21ocknprpmkw7exynfekubt9a (id, REV), 
        add constraint FK_21ocknprpmkw7exynfekubt9a 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_HEADLINE_PARAM_INST 
        add index FK_ti9adimawmve37scss7pqujv1 (id), 
        add constraint FK_ti9adimawmve37scss7pqujv1 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_HEADLINE_PARAM_INST_A 
        add index FK_6ks23r14rwqm6d2eqiqymm1ix (id, REV), 
        add constraint FK_6ks23r14rwqm6d2eqiqymm1ix 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_HIERARCHICAL_ACE 
        add index FK_etgqf62588kl8xqfh8ty5u2n4 (id), 
        add constraint FK_etgqf62588kl8xqfh8ty5u2n4 
        foreign key (id) 
        references RS_ACE (ENTITY_ID);

    alter table RS_HIERARCHICAL_ACE_A 
        add index FK_19k0yj2gdu19qj2f8caw80sv6 (id, REV), 
        add constraint FK_19k0yj2gdu19qj2f8caw80sv6 
        foreign key (id, REV) 
        references RS_ACE_A (ENTITY_ID, REV);

    alter table RS_HIERARCHICAL_ACL 
        add index FK_aifb3nnucrpxcglclathokhf5 (id), 
        add constraint FK_aifb3nnucrpxcglclathokhf5 
        foreign key (id) 
        references RS_ACL (ENTITY_ID);

    alter table RS_HIERARCHICAL_ACL_A 
        add index FK_ennc41wygdw4nwtql6ds5n9d3 (id, REV), 
        add constraint FK_ennc41wygdw4nwtql6ds5n9d3 
        foreign key (id, REV) 
        references RS_ACL_A (ENTITY_ID, REV);

    alter table RS_JASPER_REPORT 
        add index FK_l11yjbv1njmovp1ea9qocos9h (master_file_id), 
        add constraint FK_l11yjbv1njmovp1ea9qocos9h 
        foreign key (master_file_id) 
        references RS_JASPER_REPORT_JRXML (ENTITY_ID);

    alter table RS_JASPER_REPORT 
        add index FK_23h58cdkod622egqdtuhwdwuj (id), 
        add constraint FK_23h58cdkod622egqdtuhwdwuj 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_JASPER_REPORT_2_SUB_JRXML 
        add constraint UK_enrkioxfah39w3v4hlelycmlj unique (sub_files_id);

    alter table RS_JASPER_REPORT_2_SUB_JRXML 
        add index FK_enrkioxfah39w3v4hlelycmlj (sub_files_id), 
        add constraint FK_enrkioxfah39w3v4hlelycmlj 
        foreign key (sub_files_id) 
        references RS_JASPER_REPORT_JRXML (ENTITY_ID);

    alter table RS_JASPER_REPORT_2_SUB_JRXML 
        add index FK_565e3r4hvlebn6o8vrutg3l7i (jasper_report_id), 
        add constraint FK_565e3r4hvlebn6o8vrutg3l7i 
        foreign key (jasper_report_id) 
        references RS_JASPER_REPORT (id);

    alter table RS_JASPER_REPORT_2_SUB_JRXML_A 
        add index FK_22hxq622msqtjaug68gbwhlfh (REV), 
        add constraint FK_22hxq622msqtjaug68gbwhlfh 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_JASPER_REPORT_A 
        add index FK_5sei11boinenj17bpta5kxmcm (id, REV), 
        add constraint FK_5sei11boinenj17bpta5kxmcm 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_JASPER_REPORT_JRXML_A 
        add index FK_2ov02n7l6g0107t5yakw94pmn (REV), 
        add constraint FK_2ov02n7l6g0107t5yakw94pmn 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_JASPER_REPORT_VARIANT 
        add index FK_qwuj17evvq2g1qy7kg4i8swx6 (id), 
        add constraint FK_qwuj17evvq2g1qy7kg4i8swx6 
        foreign key (id) 
        references RS_JASPER_REPORT (id);

    alter table RS_JASPER_REPORT_VARIANT_A 
        add index FK_807igb9snkb41fu5q19g76cc6 (id, REV), 
        add constraint FK_807igb9snkb41fu5q19g76cc6 
        foreign key (id, REV) 
        references RS_JASPER_REPORT_A (id, REV);

    alter table RS_JASPER_TO_TABLE_CONFIG 
        add index FK_j1vn9xcsyhn3t810dfeuverl9 (datasource_container_id), 
        add constraint FK_j1vn9xcsyhn3t810dfeuverl9 
        foreign key (datasource_container_id) 
        references RS_DATASOURCE_CONTAINER (ENTITY_ID);

    alter table RS_JASPER_TO_TABLE_CONFIG 
        add index FK_lyk8blcrkfr90jwyuke9ni99b (id), 
        add constraint FK_lyk8blcrkfr90jwyuke9ni99b 
        foreign key (id) 
        references RS_REPORT_PROPERTY (ENTITY_ID);

    alter table RS_JASPER_TO_TABLE_CONFIG_A 
        add index FK_akctqjbiv039jqxdiokv8u6d5 (id, REV), 
        add constraint FK_akctqjbiv039jqxdiokv8u6d5 
        foreign key (id, REV) 
        references RS_REPORT_PROPERTY_A (ENTITY_ID, REV);

    alter table RS_JXLS_REPORT 
        add index FK_p1r4p11v2pow41ssa2nm70q (report_file_id), 
        add constraint FK_p1r4p11v2pow41ssa2nm70q 
        foreign key (report_file_id) 
        references RS_JXLS_REPORT_FILE (ENTITY_ID);

    alter table RS_JXLS_REPORT 
        add index FK_l5i4qr612tr8s1kji6crl8tmh (id), 
        add constraint FK_l5i4qr612tr8s1kji6crl8tmh 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_JXLS_REPORT_A 
        add index FK_jn2cq8w1v071yot8nmwtpij5 (id, REV), 
        add constraint FK_jn2cq8w1v071yot8nmwtpij5 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_JXLS_REPORT_FILE_A 
        add index FK_9j0xhejppqgeoj2miyhnb2oqa (REV), 
        add constraint FK_9j0xhejppqgeoj2miyhnb2oqa 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_JXLS_REPORT_VARIANT 
        add index FK_r0h3f0f7e93rt4nt0eeo3op75 (id), 
        add constraint FK_r0h3f0f7e93rt4nt0eeo3op75 
        foreign key (id) 
        references RS_JXLS_REPORT (id);

    alter table RS_JXLS_REPORT_VARIANT_A 
        add index FK_nu5wnh5x3bnr4wmsn7pkvd338 (id, REV), 
        add constraint FK_nu5wnh5x3bnr4wmsn7pkvd338 
        foreign key (id, REV) 
        references RS_JXLS_REPORT_A (id, REV);

    alter table RS_LIST_USERVARIABLE_DEF 
        add index FK_rrfldyqd2ne6gpur2wkt9dpk0 (id), 
        add constraint FK_rrfldyqd2ne6gpur2wkt9dpk0 
        foreign key (id) 
        references RS_USERVAR_DEF (ENTITY_ID);

    alter table RS_LIST_USERVARIABLE_DEF_A 
        add index FK_5anuu3qv0d3flh5qn5c9v8h9o (id, REV), 
        add constraint FK_5anuu3qv0d3flh5qn5c9v8h9o 
        foreign key (id, REV) 
        references RS_USERVAR_DEF_A (ENTITY_ID, REV);

    alter table RS_LIST_USERVARIABLE_INST 
        add index FK_g96rbkvi0gwtn6sg0foid2c28 (id), 
        add constraint FK_g96rbkvi0gwtn6sg0foid2c28 
        foreign key (id) 
        references RS_USERVAR_INST (ENTITY_ID);

    alter table RS_LIST_USERVARIABLE_INST_A 
        add index FK_brcttjhuog41jvm48xc36ohce (id, REV), 
        add constraint FK_brcttjhuog41jvm48xc36ohce 
        foreign key (id, REV) 
        references RS_USERVAR_INST_A (ENTITY_ID, REV);

    alter table RS_LIST_USERVARIABLE_INST_VL 
        add index FK_f76li8toefjvd1j0kcdpalooa (list_user_var_instanc_id), 
        add constraint FK_f76li8toefjvd1j0kcdpalooa 
        foreign key (list_user_var_instanc_id) 
        references RS_LIST_USERVARIABLE_INST (id);

    alter table RS_LIST_USERVARIABLE_INST_VL_A 
        add index FK_jqciaxerxrc6us5owylr1ug7v (REV), 
        add constraint FK_jqciaxerxrc6us5owylr1ug7v 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_MONDRIAN_DATASOURCE 
        add index FK_ntwogjogyo0kdttpvnqlu4i0e (id), 
        add constraint FK_ntwogjogyo0kdttpvnqlu4i0e 
        foreign key (id) 
        references RS_DATASOURCE_DEFINITION (id);

    alter table RS_MONDRIAN_DATASOURCE_A 
        add index FK_t9nd6b6kt10u085gikhv3y7eu (id, REV), 
        add constraint FK_t9nd6b6kt10u085gikhv3y7eu 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEFINITION_A (id, REV);

    alter table RS_MONDRIAN_DATASOURCE_CFG 
        add index FK_5083kxe1rik300f8ni7nojnrb (id), 
        add constraint FK_5083kxe1rik300f8ni7nojnrb 
        foreign key (id) 
        references RS_DATASOURCE_DEF_CONFIG (ENTITY_ID);

    alter table RS_MONDRIAN_DATASOURCE_CFG_A 
        add index FK_2suyjl7dstmvdp4ktpuhwjamt (id, REV), 
        add constraint FK_2suyjl7dstmvdp4ktpuhwjamt 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEF_CONFIG_A (ENTITY_ID, REV);

    alter table RS_ORGANISATIONAL_UNIT 
        add index FK_snfmro84ormfh6hvieufac3yp (id), 
        add constraint FK_snfmro84ormfh6hvieufac3yp 
        foreign key (id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_ORGANISATIONAL_UNIT_A 
        add index FK_eeot7bbsxqni6q9usynmtqmy4 (id, REV), 
        add constraint FK_eeot7bbsxqni6q9usynmtqmy4 
        foreign key (id, REV) 
        references RS_USERMANAGER_NODE_A (ENTITY_ID, REV);

    alter table RS_PARAMETER_DEFINITION_A 
        add index FK_q38wewcv8h7r6v63529luaqeh (REV), 
        add constraint FK_q38wewcv8h7r6v63529luaqeh 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_PARAMETER_INSTANCE 
        add index FK_73sf6r0me72wsfj4by5k9fwlj (definition_id), 
        add constraint FK_73sf6r0me72wsfj4by5k9fwlj 
        foreign key (definition_id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_PARAMETER_INSTANCE_A 
        add index FK_o4en2m656kn854q8sm5tmfhdr (REV), 
        add constraint FK_o4en2m656kn854q8sm5tmfhdr 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_PARAM_DEF_2_DEPENDANTS 
        add index FK_lfc85pfpi83in30g3nljymrix (depends_on_id), 
        add constraint FK_lfc85pfpi83in30g3nljymrix 
        foreign key (depends_on_id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_PARAM_DEF_2_DEPENDANTS 
        add index FK_g0ew2qd1kcms8snlfl3jb62v2 (parameter_definition_id), 
        add constraint FK_g0ew2qd1kcms8snlfl3jb62v2 
        foreign key (parameter_definition_id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_PARAM_DEF_2_DEPENDANTS_A 
        add index FK_8qif3eh0lj56b1t2dpo32458q (REV), 
        add constraint FK_8qif3eh0lj56b1t2dpo32458q 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_PRE_FILTER 
        add index FK_7g842n4n7qc14dh6nrkmg5o8a (root_block_id), 
        add constraint FK_7g842n4n7qc14dh6nrkmg5o8a 
        foreign key (root_block_id) 
        references RS_FILTER_BLOCK (ENTITY_ID);

    alter table RS_PRE_FILTER_A 
        add index FK_27yvhr1uff1camvgjdupk1oyj (REV), 
        add constraint FK_27yvhr1uff1camvgjdupk1oyj 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_PROPERTY 
        add constraint UK_7ft41kgrqo512yqm2jslldcgt unique (KEY_FIELD);

    alter table RS_REPORT 
        add constraint UK_el4v5i2gwp2su73awtxgprrb4 unique (uuid);

    alter table RS_REPORT 
        add index FK_h19uv1hatydac9sl0ay9skbin (datasource_container_id), 
        add constraint FK_h19uv1hatydac9sl0ay9skbin 
        foreign key (datasource_container_id) 
        references RS_DATASOURCE_CONTAINER (ENTITY_ID);

    alter table RS_REPORT 
        add index FK_gckewftig62c29j8hcfv55y7l (preview_image_id), 
        add constraint FK_gckewftig62c29j8hcfv55y7l 
        foreign key (preview_image_id) 
        references RS_REPORT_PREVIEW_IMAGE (ENTITY_ID);

    alter table RS_REPORT 
        add index FK_g8s3emmft6b0e0j9gk66mxose (id), 
        add constraint FK_g8s3emmft6b0e0j9gk66mxose 
        foreign key (id) 
        references RS_REPORT_MNGR_NODE (ENTITY_ID);

    alter table RS_REPORT_2_METADATA 
        add constraint UK_6i5w78noq3wudquppcrhteic6 unique (report_metadata_id);

    alter table RS_REPORT_2_METADATA 
        add index FK_6i5w78noq3wudquppcrhteic6 (report_metadata_id), 
        add constraint FK_6i5w78noq3wudquppcrhteic6 
        foreign key (report_metadata_id) 
        references RS_REPORT_METADATA (ENTITY_ID);

    alter table RS_REPORT_2_METADATA 
        add index FK_34eyyl4267w1xgbnxylcjwo8d (report_id), 
        add constraint FK_34eyyl4267w1xgbnxylcjwo8d 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_REPORT_2_METADATA_A 
        add index FK_84nc8n0mkxkyone63xa7yek3w (REV), 
        add constraint FK_84nc8n0mkxkyone63xa7yek3w 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_2_PARAM_DEF 
        add constraint UK_3yll7c2ghqhdhfasxcnjsbetp unique (parameter_definitions_id);

    alter table RS_REPORT_2_PARAM_DEF 
        add index FK_3yll7c2ghqhdhfasxcnjsbetp (parameter_definitions_id), 
        add constraint FK_3yll7c2ghqhdhfasxcnjsbetp 
        foreign key (parameter_definitions_id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_REPORT_2_PARAM_DEF 
        add index FK_5n1n9gkx7ixqkl6alqd8g75u7 (report_id), 
        add constraint FK_5n1n9gkx7ixqkl6alqd8g75u7 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_REPORT_2_PARAM_DEF_A 
        add index FK_fhvq3rc7fi4gw5fbnwj9pid6p (REV), 
        add constraint FK_fhvq3rc7fi4gw5fbnwj9pid6p 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_2_PARAM_INST 
        add constraint UK_fhvhwsuq47ihq2ukjfk7jenas unique (parameter_instances_id);

    alter table RS_REPORT_2_PARAM_INST 
        add index FK_fhvhwsuq47ihq2ukjfk7jenas (parameter_instances_id), 
        add constraint FK_fhvhwsuq47ihq2ukjfk7jenas 
        foreign key (parameter_instances_id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_REPORT_2_PARAM_INST 
        add index FK_kgvhxlcdaknn1u6qy0o9me8sv (report_id), 
        add constraint FK_kgvhxlcdaknn1u6qy0o9me8sv 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_REPORT_2_PARAM_INST_A 
        add index FK_3wvyadndqupmo5rkj06ab0ub (REV), 
        add constraint FK_3wvyadndqupmo5rkj06ab0ub 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_2_PROPERTY 
        add constraint UK_dp9f61gqyqds5xabdyxfhck5b unique (report_properties_id);

    alter table RS_REPORT_2_PROPERTY 
        add index FK_dp9f61gqyqds5xabdyxfhck5b (report_properties_id), 
        add constraint FK_dp9f61gqyqds5xabdyxfhck5b 
        foreign key (report_properties_id) 
        references RS_REPORT_PROPERTY (ENTITY_ID);

    alter table RS_REPORT_2_PROPERTY 
        add index FK_qq9l1wie5bfc9d1nyn4dhsv24 (report_id), 
        add constraint FK_qq9l1wie5bfc9d1nyn4dhsv24 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_REPORT_2_PROPERTY_A 
        add index FK_mdiaus1a4e2fuf7cw2ehs4uj9 (REV), 
        add constraint FK_mdiaus1a4e2fuf7cw2ehs4uj9 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_A 
        add index FK_5rnn2esh37cjspj90wj3vimvv (id, REV), 
        add constraint FK_5rnn2esh37cjspj90wj3vimvv 
        foreign key (id, REV) 
        references RS_REPORT_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_REPORT_BYTE_PROPERTY 
        add index FK_69x0a61mqnrch72mvq4epxo1t (id), 
        add constraint FK_69x0a61mqnrch72mvq4epxo1t 
        foreign key (id) 
        references RS_REPORT_PROPERTY (ENTITY_ID);

    alter table RS_REPORT_BYTE_PROPERTY_A 
        add index FK_53r88n3c7br6lj0jneqpmqte (id, REV), 
        add constraint FK_53r88n3c7br6lj0jneqpmqte 
        foreign key (id, REV) 
        references RS_REPORT_PROPERTY_A (ENTITY_ID, REV);

    alter table RS_REPORT_FOLDER 
        add index FK_q41iboj4yjqmeepdbb8i9iwht (id), 
        add constraint FK_q41iboj4yjqmeepdbb8i9iwht 
        foreign key (id) 
        references RS_REPORT_MNGR_NODE (ENTITY_ID);

    alter table RS_REPORT_FOLDER_A 
        add index FK_esehmpm6nwtyhne6ypea3ny9q (id, REV), 
        add constraint FK_esehmpm6nwtyhne6ypea3ny9q 
        foreign key (id, REV) 
        references RS_REPORT_MNGR_NODE_A (ENTITY_ID, REV);

    alter table RS_REPORT_METADATA_A 
        add index FK_jkinh1udh1drnebwm16tmgxkl (REV), 
        add constraint FK_jkinh1udh1drnebwm16tmgxkl 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_MNGR_NODE 
        add index FK_mwe3bb340w0oslt4a9828r6u6 (parent_id), 
        add constraint FK_mwe3bb340w0oslt4a9828r6u6 
        foreign key (parent_id) 
        references RS_REPORT_MNGR_NODE (ENTITY_ID);

    alter table RS_REPORT_MNGR_NODE 
        add index FK_qi7fubpe34psuxo1rjcigcuq8 (acl_id), 
        add constraint FK_qi7fubpe34psuxo1rjcigcuq8 
        foreign key (acl_id) 
        references RS_HIERARCHICAL_ACL (id);

    alter table RS_REPORT_MNGR_NODE 
        add index FK_t9fdglcqwigt76k4rlc7v4tqy (owner_id), 
        add constraint FK_t9fdglcqwigt76k4rlc7v4tqy 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_REPORT_MNGR_NODE_A 
        add index FK_jes7y0l4ukkdctgp90uklcxl6 (REV), 
        add constraint FK_jes7y0l4ukkdctgp90uklcxl6 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_PREVIEW_IMAGE_A 
        add index FK_rn15aj05e2k3jteiotvuvv3lx (REV), 
        add constraint FK_rn15aj05e2k3jteiotvuvv3lx 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_PROPERTY_A 
        add index FK_jbenkocy50686acb1lq7p6hbl (REV), 
        add constraint FK_jbenkocy50686acb1lq7p6hbl 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_REPORT_SS_STRING_PROPERTY 
        add index FK_3ghg0k7p10mn9fa2j5x8vixbw (id), 
        add constraint FK_3ghg0k7p10mn9fa2j5x8vixbw 
        foreign key (id) 
        references RS_REPORT_PROPERTY (ENTITY_ID);

    alter table RS_REPORT_SS_STRING_PROPERTY_A 
        add index FK_bql1sl591waa80ptvy09mdppn (id, REV), 
        add constraint FK_bql1sl591waa80ptvy09mdppn 
        foreign key (id, REV) 
        references RS_REPORT_PROPERTY_A (ENTITY_ID, REV);

    alter table RS_REPORT_STRING_PROPERTY 
        add index FK_7e6qu0bsp7xbp2326bnlmcisb (id), 
        add constraint FK_7e6qu0bsp7xbp2326bnlmcisb 
        foreign key (id) 
        references RS_REPORT_PROPERTY (ENTITY_ID);

    alter table RS_REPORT_STRING_PROPERTY_A 
        add index FK_108oa53uv9qcbolexvf10b8fw (id, REV), 
        add constraint FK_108oa53uv9qcbolexvf10b8fw 
        foreign key (id, REV) 
        references RS_REPORT_PROPERTY_A (ENTITY_ID, REV);

    alter table RS_SAIKU_REPORT 
        add index FK_qjfa14dtogrti08mibh5ha6tf (id), 
        add constraint FK_qjfa14dtogrti08mibh5ha6tf 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_SAIKU_REPORT_A 
        add index FK_cr11j84chwnpwbwj9jq850out (id, REV), 
        add constraint FK_cr11j84chwnpwbwj9jq850out 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_SAIKU_REPORT_VARIANT 
        add index FK_8vis4ckovmfjld4kxmugchal9 (id), 
        add constraint FK_8vis4ckovmfjld4kxmugchal9 
        foreign key (id) 
        references RS_SAIKU_REPORT (id);

    alter table RS_SAIKU_REPORT_VARIANT_A 
        add index FK_m5ee077dapfeqvwhtv750scqm (id, REV), 
        add constraint FK_m5ee077dapfeqvwhtv750scqm 
        foreign key (id, REV) 
        references RS_SAIKU_REPORT_A (id, REV);

    alter table RS_SCHED_ACTION_AS_FILE 
        add index FK_3ugo18pvdsa297ovygqk9ir2d (id), 
        add constraint FK_3ugo18pvdsa297ovygqk9ir2d 
        foreign key (id) 
        references RS_SCHED_ACTION (ENTITY_ID);

    alter table RS_SCHED_ACTION_AS_FTP_FILE 
        add index FK_b9l2p96sendgnupeeur0qlogj (ftp_datasink_id), 
        add constraint FK_b9l2p96sendgnupeeur0qlogj 
        foreign key (ftp_datasink_id) 
        references RS_FTP_DATASINK (id);

    alter table RS_SCHED_ACTION_AS_FTP_FILE 
        add index FK_cumgqtqnj7e0hrj021w5oj29d (id), 
        add constraint FK_cumgqtqnj7e0hrj021w5oj29d 
        foreign key (id) 
        references RS_SCHED_ACTION (ENTITY_ID);

    alter table RS_SCHED_ACTION_AS_SFTP_FILE 
        add index FK_l52q0t61gkl45w1xybv7n39pg (sftp_datasink_id), 
        add constraint FK_l52q0t61gkl45w1xybv7n39pg 
        foreign key (sftp_datasink_id) 
        references RS_SFTP_DATASINK (id);

    alter table RS_SCHED_ACTION_AS_SFTP_FILE 
        add index FK_85ma3vqnjbveo8wwh3cdvit9h (id), 
        add constraint FK_85ma3vqnjbveo8wwh3cdvit9h 
        foreign key (id) 
        references RS_SCHED_ACTION (ENTITY_ID);

    alter table RS_SCHED_ACTION_ENT_2_PROP 
        add constraint UK_sm55x020im9umyfbqtablqv37 unique (history_properties_id);

    alter table RS_SCHED_ACTION_ENT_2_PROP 
        add index FK_sm55x020im9umyfbqtablqv37 (history_properties_id), 
        add constraint FK_sm55x020im9umyfbqtablqv37 
        foreign key (history_properties_id) 
        references RS_SCHED_HIST_ENTRY_PROPERTY (ENTITY_ID);

    alter table RS_SCHED_ACTION_ENT_2_PROP 
        add index FK_7slp61cap7jvg8ubtpsus1ics (sched_hist_action_entry_id), 
        add constraint FK_7slp61cap7jvg8ubtpsus1ics 
        foreign key (sched_hist_action_entry_id) 
        references RS_SCHED_HIST_ACTION_ENTRY (ENTITY_ID);

    alter table RS_SCHED_ACTION_MAIL_REPORT 
        add index FK_3fhcwp01wtvc1ef9hnw5gpxnf (id), 
        add constraint FK_3fhcwp01wtvc1ef9hnw5gpxnf 
        foreign key (id) 
        references RS_SCHED_ACTION (ENTITY_ID);

    alter table RS_SCHED_ACTION_SEND_TO 
        add index FK_rvx3r39xe6drjb8h6kly23si (id), 
        add constraint FK_rvx3r39xe6drjb8h6kly23si 
        foreign key (id) 
        references RS_SCHED_ACTION (ENTITY_ID);

    alter table RS_SCHED_ACTION_SEND_TO_V 
        add index FK_tfquvr60i8a91r9mqn47goep2 (SEND_TO), 
        add constraint FK_tfquvr60i8a91r9mqn47goep2 
        foreign key (SEND_TO) 
        references RS_SCHED_ACTION_SEND_TO (id);

    alter table RS_SCHED_EXECUTE_REPORT_JOB 
        add index FK_fmvat674w2dcjnq4gr18fl4i8 (executor_id), 
        add constraint FK_fmvat674w2dcjnq4gr18fl4i8 
        foreign key (executor_id) 
        references RS_USER (id);

    alter table RS_SCHED_EXECUTE_REPORT_JOB 
        add index FK_9c91ncvmapb7chgbb7uv02ld6 (scheduled_by_id), 
        add constraint FK_9c91ncvmapb7chgbb7uv02ld6 
        foreign key (scheduled_by_id) 
        references RS_USER (id);

    alter table RS_SCHED_EXECUTE_REPORT_JOB 
        add index FK_mhdjs8da4id4udlsxombvm4qn (report_id), 
        add constraint FK_mhdjs8da4id4udlsxombvm4qn 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_SCHED_EXECUTE_REPORT_JOB 
        add index FK_asu9llr8v7dp58p91xopjekx8 (id), 
        add constraint FK_asu9llr8v7dp58p91xopjekx8 
        foreign key (id) 
        references RS_SCHED_JOB (ENTITY_ID);

    alter table RS_SCHED_EXECUTE_SCRIPT_JOB 
        add index FK_ajtcq5d6mp4bdtg84mqdrbg9h (executor_id), 
        add constraint FK_ajtcq5d6mp4bdtg84mqdrbg9h 
        foreign key (executor_id) 
        references RS_USER (id);

    alter table RS_SCHED_EXECUTE_SCRIPT_JOB 
        add index FK_ag4u7ntsj4k38buq7apbp2ew4 (scheduled_by_id), 
        add constraint FK_ag4u7ntsj4k38buq7apbp2ew4 
        foreign key (scheduled_by_id) 
        references RS_USER (id);

    alter table RS_SCHED_EXECUTE_SCRIPT_JOB 
        add index FK_8nugydrc76own6uvdornixvpi (id), 
        add constraint FK_8nugydrc76own6uvdornixvpi 
        foreign key (id) 
        references RS_SCHED_JOB (ENTITY_ID);

    alter table RS_SCHED_HIST_ACTION_ENTRY 
        add index FK_n43oxq13mx976ne4ivrje5r2g (EXEC_ENTRY), 
        add constraint FK_n43oxq13mx976ne4ivrje5r2g 
        foreign key (EXEC_ENTRY) 
        references RS_SCHED_HIST_EXEC_ENTRY (ENTITY_ID);

    alter table RS_SCHED_HIST_EXEC_ENTRY 
        add index FK_irw7ypo0g7csnlli3jc3s6xxa (job_entry_id), 
        add constraint FK_irw7ypo0g7csnlli3jc3s6xxa 
        foreign key (job_entry_id) 
        references RS_SCHED_HIST_JOB_ENTRY (ENTITY_ID);

    alter table RS_SCHED_HIST_EXEC_ENTRY 
        add index FK_8d3kc7qbbgs4msajs7cvjh1b3 (JOB_HISTORY), 
        add constraint FK_8d3kc7qbbgs4msajs7cvjh1b3 
        foreign key (JOB_HISTORY) 
        references RS_SCHEDULER_JOB_HISTORY (ENTITY_ID);

    alter table RS_SCHED_JOB 
        add index FK_anwrd37wjm42uu11ejo94jmny (history_id), 
        add constraint FK_anwrd37wjm42uu11ejo94jmny 
        foreign key (history_id) 
        references RS_SCHEDULER_JOB_HISTORY (ENTITY_ID);

    alter table RS_SCHED_JOB 
        add index FK_h1ntaxxckwq4lohg53jwaptg0 (link_to_previous_id), 
        add constraint FK_h1ntaxxckwq4lohg53jwaptg0 
        foreign key (link_to_previous_id) 
        references RS_SCHED_JOB (ENTITY_ID);

    alter table RS_SCHED_JOB 
        add index FK_cl72jav3wra3j6ny382fm7v7k (trigger_id), 
        add constraint FK_cl72jav3wra3j6ny382fm7v7k 
        foreign key (trigger_id) 
        references RS_SCHED_TRIGGER (ENTITY_ID);

    alter table RS_SCHED_JOB_2_ACTIONS 
        add constraint UK_iojoggv0cic92g6k5et5nd9oh unique (actions_id);

    alter table RS_SCHED_JOB_2_ACTIONS 
        add index FK_iojoggv0cic92g6k5et5nd9oh (actions_id), 
        add constraint FK_iojoggv0cic92g6k5et5nd9oh 
        foreign key (actions_id) 
        references RS_SCHED_ACTION (ENTITY_ID);

    alter table RS_SCHED_JOB_2_ACTIONS 
        add index FK_jnbm406xxnu3qnqeo64adta4l (sched_job_id), 
        add constraint FK_jnbm406xxnu3qnqeo64adta4l 
        foreign key (sched_job_id) 
        references RS_SCHED_JOB (ENTITY_ID);

    alter table RS_SCHED_JOB_2_OWNER 
        add index FK_aubvcvw9n3g4deyw500dnn8r3 (owners_id), 
        add constraint FK_aubvcvw9n3g4deyw500dnn8r3 
        foreign key (owners_id) 
        references RS_USER (id);

    alter table RS_SCHED_JOB_2_OWNER 
        add index FK_f8gs3rkk6779a5cby7lp3u3iw (sched_execute_report_job_id), 
        add constraint FK_f8gs3rkk6779a5cby7lp3u3iw 
        foreign key (sched_execute_report_job_id) 
        references RS_SCHED_EXECUTE_REPORT_JOB (id);

    alter table RS_SCHED_JOB_ENT_2_PROP 
        add constraint UK_mcjcrxant3km2dbeogkflg94n unique (history_properties_id);

    alter table RS_SCHED_JOB_ENT_2_PROP 
        add index FK_mcjcrxant3km2dbeogkflg94n (history_properties_id), 
        add constraint FK_mcjcrxant3km2dbeogkflg94n 
        foreign key (history_properties_id) 
        references RS_SCHED_HIST_ENTRY_PROPERTY (ENTITY_ID);

    alter table RS_SCHED_JOB_ENT_2_PROP 
        add index FK_dbe4wn36fe0y4tc3mmk4yyscc (sched_hist_job_entry_id), 
        add constraint FK_dbe4wn36fe0y4tc3mmk4yyscc 
        foreign key (sched_hist_job_entry_id) 
        references RS_SCHED_HIST_JOB_ENTRY (ENTITY_ID);

    alter table RS_SCHED_REP_EXEC_JOB_2_PROP 
        add constraint UK_30b2g5yxxj3yea9ft5g9v0xef unique (base_properties_id);

    alter table RS_SCHED_REP_EXEC_JOB_2_PROP 
        add index FK_30b2g5yxxj3yea9ft5g9v0xef (base_properties_id), 
        add constraint FK_30b2g5yxxj3yea9ft5g9v0xef 
        foreign key (base_properties_id) 
        references RS_SCHED_BASE_PROPERTY (ENTITY_ID);

    alter table RS_SCHED_REP_EXEC_JOB_2_PROP 
        add index FK_gb4f7gkow2t3f42rvu8y2f16t (sched_execute_report_job_id), 
        add constraint FK_gb4f7gkow2t3f42rvu8y2f16t 
        foreign key (sched_execute_report_job_id) 
        references RS_SCHED_EXECUTE_REPORT_JOB (id);

    alter table RS_SCHED_REP_EXEC_JOB_2_RCPT 
        add index FK_3rpfe7y72y16stsh63tvm3p5x (report_execute_job_id), 
        add constraint FK_3rpfe7y72y16stsh63tvm3p5x 
        foreign key (report_execute_job_id) 
        references RS_SCHED_EXECUTE_REPORT_JOB (id);

    alter table RS_SCHED_SCR_EXE_JOB_2_PROP 
        add constraint UK_s1i45snybrphhnjf8og5at0nv unique (base_properties_id);

    alter table RS_SCHED_SCR_EXE_JOB_2_PROP 
        add index FK_s1i45snybrphhnjf8og5at0nv (base_properties_id), 
        add constraint FK_s1i45snybrphhnjf8og5at0nv 
        foreign key (base_properties_id) 
        references RS_SCHED_BASE_PROPERTY (ENTITY_ID);

    alter table RS_SCHED_SCR_EXE_JOB_2_PROP 
        add index FK_sgilgr5t1dp64o9nnnhwt3r06 (sched_execute_script_job_id), 
        add constraint FK_sgilgr5t1dp64o9nnnhwt3r06 
        foreign key (sched_execute_script_job_id) 
        references RS_SCHED_EXECUTE_SCRIPT_JOB (id);

    alter table RS_SCHED_TRIG_DAILY_NTHDAY 
        add index FK_r1lhxjxon8gdfdn2ov9twlira (id), 
        add constraint FK_r1lhxjxon8gdfdn2ov9twlira 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCHED_TRIG_DAILY_WORKDAY 
        add index FK_4ruamdrydf8v4lqxf3ke1egip (id), 
        add constraint FK_4ruamdrydf8v4lqxf3ke1egip 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCHED_TRIG_DATE 
        add index FK_s5b3c126h4gcwri0mxjh5o3uk (config_id), 
        add constraint FK_s5b3c126h4gcwri0mxjh5o3uk 
        foreign key (config_id) 
        references RS_DATE_TRIGGER_CONFIG (ENTITY_ID);

    alter table RS_SCHED_TRIG_DATE 
        add index FK_p5p5ewb1hw1k3rpt82vldeiwl (id), 
        add constraint FK_p5p5ewb1hw1k3rpt82vldeiwl 
        foreign key (id) 
        references RS_SCHED_TRIGGER (ENTITY_ID);

    alter table RS_SCHED_TRIG_MONTH_NAMED_DAY 
        add index FK_li87ed1urn8oofagasivsc4ow (id), 
        add constraint FK_li87ed1urn8oofagasivsc4ow 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCHED_TRIG_MON_DAY_O_MON 
        add index FK_92wwtm7elhb0ik8338murj1ym (id), 
        add constraint FK_92wwtm7elhb0ik8338murj1ym 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCHED_TRIG_WEEKLY 
        add index FK_g363mlp2xollf57p5c1hukx5 (id), 
        add constraint FK_g363mlp2xollf57p5c1hukx5 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCHED_TRIG_YEARLY_AT_DATE 
        add index FK_k0m1fjuplal6dcsg06ee52cdo (id), 
        add constraint FK_k0m1fjuplal6dcsg06ee52cdo 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCHED_TRIG_YEAR_NAMED_DAY 
        add index FK_gryn0egsnupgq9sy6dusq1ijf (id), 
        add constraint FK_gryn0egsnupgq9sy6dusq1ijf 
        foreign key (id) 
        references RS_SCHED_TRIG_DATE (id);

    alter table RS_SCRIPT_DATASOURCE 
        add index FK_irdvekiotse14yohth374g23 (script_id), 
        add constraint FK_irdvekiotse14yohth374g23 
        foreign key (script_id) 
        references RS_FILE_SERVER_FILE (id);

    alter table RS_SCRIPT_DATASOURCE 
        add index FK_8eswye6xd24y6apoydoc21swf (id), 
        add constraint FK_8eswye6xd24y6apoydoc21swf 
        foreign key (id) 
        references RS_DATASOURCE_DEFINITION (id);

    alter table RS_SCRIPT_DATASOURCE_A 
        add index FK_lm4es2m25mcled12cx8ateae6 (id, REV), 
        add constraint FK_lm4es2m25mcled12cx8ateae6 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEFINITION_A (id, REV);

    alter table RS_SCRIPT_DATASOURCE_CONFIG 
        add index FK_sr84mf9sha5jrfmmnaov1dcs (id), 
        add constraint FK_sr84mf9sha5jrfmmnaov1dcs 
        foreign key (id) 
        references RS_DATASOURCE_DEF_CONFIG (ENTITY_ID);

    alter table RS_SCRIPT_DATASOURCE_CONFIG_A 
        add index FK_t3t96bes0a3wf0dvv6lx4u8mm (id, REV), 
        add constraint FK_t3t96bes0a3wf0dvv6lx4u8mm 
        foreign key (id, REV) 
        references RS_DATASOURCE_DEF_CONFIG_A (ENTITY_ID, REV);

    alter table RS_SCRIPT_PARAM_DEF 
        add index FK_hfnv1c27ihh81sre9wvifh7o4 (script_id), 
        add constraint FK_hfnv1c27ihh81sre9wvifh7o4 
        foreign key (script_id) 
        references RS_FILE_SERVER_FILE (id);

    alter table RS_SCRIPT_PARAM_DEF 
        add index FK_poie3uhiaulamcdpk8aqjo26e (id), 
        add constraint FK_poie3uhiaulamcdpk8aqjo26e 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_SCRIPT_PARAM_DEF_A 
        add index FK_aejry1mhcah90snme3yp9bpu3 (id, REV), 
        add constraint FK_aejry1mhcah90snme3yp9bpu3 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_SCRIPT_PARAM_INST 
        add index FK_888ajc6mlovv65gj603w5b6rq (id), 
        add constraint FK_888ajc6mlovv65gj603w5b6rq 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_SCRIPT_PARAM_INST_A 
        add index FK_psltq1csjn3sxygca546f41yj (id, REV), 
        add constraint FK_psltq1csjn3sxygca546f41yj 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_SCRIPT_REPORT 
        add index FK_f3c5fca1shtm1pcvfxuvbddhp (script_id), 
        add constraint FK_f3c5fca1shtm1pcvfxuvbddhp 
        foreign key (script_id) 
        references RS_FILE_SERVER_FILE (id);

    alter table RS_SCRIPT_REPORT 
        add index FK_19kgcc7xovaaf2y77n4ba6lvw (id), 
        add constraint FK_19kgcc7xovaaf2y77n4ba6lvw 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_SCRIPT_REPORT_2_EX_FORMAT 
        add index FK_4kcod9wrdlv2itek3vp4v8ic6 (script_report_id), 
        add constraint FK_4kcod9wrdlv2itek3vp4v8ic6 
        foreign key (script_report_id) 
        references RS_SCRIPT_REPORT (id);

    alter table RS_SCRIPT_REPORT_2_EX_FORMAT_A 
        add index FK_n2v4uam2hpku33o3kkmcs57w1 (REV), 
        add constraint FK_n2v4uam2hpku33o3kkmcs57w1 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_SCRIPT_REPORT_A 
        add index FK_ksfkkj3398imlf5aawxi6ykd1 (id, REV), 
        add constraint FK_ksfkkj3398imlf5aawxi6ykd1 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_SCRIPT_REPORT_VARIANT 
        add index FK_nwlfndvpdumb14mem9cpc6ewp (id), 
        add constraint FK_nwlfndvpdumb14mem9cpc6ewp 
        foreign key (id) 
        references RS_SCRIPT_REPORT (id);

    alter table RS_SCRIPT_REPORT_VARIANT_A 
        add index FK_k3ycymg5ctpuvdcffp4xpajdp (id, REV), 
        add constraint FK_k3ycymg5ctpuvdcffp4xpajdp 
        foreign key (id, REV) 
        references RS_SCRIPT_REPORT_A (id, REV);

    alter table RS_SEP_PARAM_DEF 
        add index FK_fxis8dwe9cm8xn9ai7jipyvns (id), 
        add constraint FK_fxis8dwe9cm8xn9ai7jipyvns 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_SEP_PARAM_DEF_A 
        add index FK_tf3520lbbt6ucpmgdmn2gi9dw (id, REV), 
        add constraint FK_tf3520lbbt6ucpmgdmn2gi9dw 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_SEP_PARAM_INST 
        add index FK_px4n130nfxibyvmpd2aanmyyn (id), 
        add constraint FK_px4n130nfxibyvmpd2aanmyyn 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_SEP_PARAM_INST_A 
        add index FK_jfqrkwjy50f09d9c359krwrqf (id, REV), 
        add constraint FK_jfqrkwjy50f09d9c359krwrqf 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_SFTP_DATASINK 
        add index FK_4e5xvbyd6f9ui7vpleeigcw4p (id), 
        add constraint FK_4e5xvbyd6f9ui7vpleeigcw4p 
        foreign key (id) 
        references RS_DATASINK_DEFINITION (id);

    alter table RS_SFTP_DATASINK_A 
        add index FK_jheb80qaijqqks3268f4xfq8g (id, REV), 
        add constraint FK_jheb80qaijqqks3268f4xfq8g 
        foreign key (id, REV) 
        references RS_DATASINK_DEFINITION_A (id, REV);

    alter table RS_STR_USERVARIABLE_DEF 
        add index FK_tpbipviec3b5qnhd7yxy4bnm2 (id), 
        add constraint FK_tpbipviec3b5qnhd7yxy4bnm2 
        foreign key (id) 
        references RS_USERVAR_DEF (ENTITY_ID);

    alter table RS_STR_USERVARIABLE_DEF_A 
        add index FK_rpuh4k2wjgcat18vahxqn0er1 (id, REV), 
        add constraint FK_rpuh4k2wjgcat18vahxqn0er1 
        foreign key (id, REV) 
        references RS_USERVAR_DEF_A (ENTITY_ID, REV);

    alter table RS_STR_USERVARIABLE_INST 
        add index FK_mfk0pjtr17j0c1bjq2b9ijo2h (id), 
        add constraint FK_mfk0pjtr17j0c1bjq2b9ijo2h 
        foreign key (id) 
        references RS_USERVAR_INST (ENTITY_ID);

    alter table RS_STR_USERVARIABLE_INST_A 
        add index FK_567nnwfkqjs9cfqbe7rb4u7eh (id, REV), 
        add constraint FK_567nnwfkqjs9cfqbe7rb4u7eh 
        foreign key (id, REV) 
        references RS_USERVAR_INST_A (ENTITY_ID, REV);

    alter table RS_TABLE_REPORT 
        add index FK_8foqv174ohfinnfjvryw5mi7o (metadata_datas_container_id), 
        add constraint FK_8foqv174ohfinnfjvryw5mi7o 
        foreign key (metadata_datas_container_id) 
        references RS_DATASOURCE_CONTAINER (ENTITY_ID);

    alter table RS_TABLE_REPORT 
        add index FK_no77bkk1y53qrinwf31agl13y (pre_filter_id), 
        add constraint FK_no77bkk1y53qrinwf31agl13y 
        foreign key (pre_filter_id) 
        references RS_PRE_FILTER (ENTITY_ID);

    alter table RS_TABLE_REPORT 
        add index FK_ssj1sc4nb1i307nwptlqfeu5k (id), 
        add constraint FK_ssj1sc4nb1i307nwptlqfeu5k 
        foreign key (id) 
        references RS_REPORT (id);

    alter table RS_TABLE_REPORT_2_ADD_COLUMN 
        add constraint UK_pbvwpa8ou8nnwjrfsydid3ts5 unique (additional_columns_id);

    alter table RS_TABLE_REPORT_2_ADD_COLUMN 
        add index FK_pbvwpa8ou8nnwjrfsydid3ts5 (additional_columns_id), 
        add constraint FK_pbvwpa8ou8nnwjrfsydid3ts5 
        foreign key (additional_columns_id) 
        references RS_ADD_COLUMN_SPEC (id);

    alter table RS_TABLE_REPORT_2_ADD_COLUMN 
        add index FK_ryq1behdl9rkvhwx6d7tly19m (table_report_id), 
        add constraint FK_ryq1behdl9rkvhwx6d7tly19m 
        foreign key (table_report_id) 
        references RS_TABLE_REPORT (id);

    alter table RS_TABLE_REPORT_2_ADD_COLUMN_A 
        add index FK_eswsunx09re1mg52w935uktfp (REV), 
        add constraint FK_eswsunx09re1mg52w935uktfp 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TABLE_REPORT_2_COLUMN 
        add constraint UK_93vi81iplycbj8ede52xau7mi unique (columns_id);

    alter table RS_TABLE_REPORT_2_COLUMN 
        add index FK_93vi81iplycbj8ede52xau7mi (columns_id), 
        add constraint FK_93vi81iplycbj8ede52xau7mi 
        foreign key (columns_id) 
        references RS_COLUMN (ENTITY_ID);

    alter table RS_TABLE_REPORT_2_COLUMN 
        add index FK_atjtyp2hvm7rrqgfgjqte0p2f (table_report_id), 
        add constraint FK_atjtyp2hvm7rrqgfgjqte0p2f 
        foreign key (table_report_id) 
        references RS_TABLE_REPORT (id);

    alter table RS_TABLE_REPORT_2_COLUMN_A 
        add index FK_937b9sj2954ktaphulqj45t2d (REV), 
        add constraint FK_937b9sj2954ktaphulqj45t2d 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TABLE_REPORT_A 
        add index FK_31f57lh1ucudwgnnserxebcs0 (id, REV), 
        add constraint FK_31f57lh1ucudwgnnserxebcs0 
        foreign key (id, REV) 
        references RS_REPORT_A (id, REV);

    alter table RS_TABLE_REPORT_BYTE_TPL 
        add index FK_l7q8egy5d801op4ranly76cy9 (id), 
        add constraint FK_l7q8egy5d801op4ranly76cy9 
        foreign key (id) 
        references RS_TABLE_REPORT_TEMPLATE (ENTITY_ID);

    alter table RS_TABLE_REPORT_BYTE_TPL_A 
        add index FK_ssgskf0blnk3kpwyqps9qwv62 (id, REV), 
        add constraint FK_ssgskf0blnk3kpwyqps9qwv62 
        foreign key (id, REV) 
        references RS_TABLE_REPORT_TEMPLATE_A (ENTITY_ID, REV);

    alter table RS_TABLE_REPORT_STR_TEMPLATE 
        add index FK_nvca7qhnnco89led1n0m8ib60 (id), 
        add constraint FK_nvca7qhnnco89led1n0m8ib60 
        foreign key (id) 
        references RS_TABLE_REPORT_TEMPLATE (ENTITY_ID);

    alter table RS_TABLE_REPORT_STR_TEMPLATE_A 
        add index FK_6cyboefahhgx4u47al8ytd1mw (id, REV), 
        add constraint FK_6cyboefahhgx4u47al8ytd1mw 
        foreign key (id, REV) 
        references RS_TABLE_REPORT_TEMPLATE_A (ENTITY_ID, REV);

    alter table RS_TABLE_REPORT_TEMPLATE_A 
        add index FK_26mdoe18vxby93xchd6ho6fht (REV), 
        add constraint FK_26mdoe18vxby93xchd6ho6fht 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TABLE_REPORT_TEMPLATE_LST 
        add index FK_jf2wxrfl94yak4iv37c40qncb (id), 
        add constraint FK_jf2wxrfl94yak4iv37c40qncb 
        foreign key (id) 
        references RS_REPORT_PROPERTY (ENTITY_ID);

    alter table RS_TABLE_REPORT_TEMPLATE_LST_A 
        add index FK_b3o1ne169kq98h8boke4vl8pw (id, REV), 
        add constraint FK_b3o1ne169kq98h8boke4vl8pw 
        foreign key (id, REV) 
        references RS_REPORT_PROPERTY_A (ENTITY_ID, REV);

    alter table RS_TABLE_REPORT_VARIANT 
        add index FK_sxv05l9cmfnxi9q7jdq5owf1a (id), 
        add constraint FK_sxv05l9cmfnxi9q7jdq5owf1a 
        foreign key (id) 
        references RS_TABLE_REPORT (id);

    alter table RS_TABLE_REPORT_VARIANT_A 
        add index FK_fu49tg245ugv1g2gxcfh6jvp6 (id, REV), 
        add constraint FK_fu49tg245ugv1g2gxcfh6jvp6 
        foreign key (id, REV) 
        references RS_TABLE_REPORT_A (id, REV);

    alter table RS_TAB_REP_TPL_LST_2_TPL 
        add constraint UK_havn4ds3ldax0582mvce0kxhs unique (templates_id);

    alter table RS_TAB_REP_TPL_LST_2_TPL 
        add index FK_havn4ds3ldax0582mvce0kxhs (templates_id), 
        add constraint FK_havn4ds3ldax0582mvce0kxhs 
        foreign key (templates_id) 
        references RS_TABLE_REPORT_TEMPLATE (ENTITY_ID);

    alter table RS_TAB_REP_TPL_LST_2_TPL 
        add index FK_p1v7grf8lsni9ujv2rhh9lbdi (table_report_templat_lst_id), 
        add constraint FK_p1v7grf8lsni9ujv2rhh9lbdi 
        foreign key (table_report_templat_lst_id) 
        references RS_TABLE_REPORT_TEMPLATE_LST (id);

    alter table RS_TAB_REP_TPL_LST_2_TPL_A 
        add index FK_fmdqyhv9sd9d1katwexpl92m7 (REV), 
        add constraint FK_fmdqyhv9sd9d1katwexpl92m7 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEAMSPACE 
        add index FK_h00mwj3cusqjf2c1ctye1xlt5 (owner_id), 
        add constraint FK_h00mwj3cusqjf2c1ctye1xlt5 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_TEAMSPACE_2_APP 
        add constraint UK_na451k4x089fwlqutv25qnjg5 unique (apps_id);

    alter table RS_TEAMSPACE_2_APP 
        add index FK_na451k4x089fwlqutv25qnjg5 (apps_id), 
        add constraint FK_na451k4x089fwlqutv25qnjg5 
        foreign key (apps_id) 
        references RS_TEAMSPACE_APP (ENTITY_ID);

    alter table RS_TEAMSPACE_2_APP 
        add index FK_5e59vga3ufgjq7okpgx83nr67 (teamspace_id), 
        add constraint FK_5e59vga3ufgjq7okpgx83nr67 
        foreign key (teamspace_id) 
        references RS_TEAMSPACE (ENTITY_ID);

    alter table RS_TEAMSPACE_2_APP_A 
        add index FK_brj64tyuakbemq6xeyjohq8s5 (REV), 
        add constraint FK_brj64tyuakbemq6xeyjohq8s5 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEAMSPACE_A 
        add index FK_rejb26cb8ewfmmv2um3myil7q (REV), 
        add constraint FK_rejb26cb8ewfmmv2um3myil7q 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEAMSPACE_APP_2_PROPERTY 
        add constraint UK_d9jtwygbdms54tiy652gkt33y unique (app_properties_id);

    alter table RS_TEAMSPACE_APP_2_PROPERTY 
        add index FK_d9jtwygbdms54tiy652gkt33y (app_properties_id), 
        add constraint FK_d9jtwygbdms54tiy652gkt33y 
        foreign key (app_properties_id) 
        references RS_TEAMSPACE_APP_PROPERTY (ENTITY_ID);

    alter table RS_TEAMSPACE_APP_2_PROPERTY 
        add index FK_tdhh36hfre5pdr9jfgkpu7icr (teamspace_app_id), 
        add constraint FK_tdhh36hfre5pdr9jfgkpu7icr 
        foreign key (teamspace_app_id) 
        references RS_TEAMSPACE_APP (ENTITY_ID);

    alter table RS_TEAMSPACE_APP_2_PROPERTY_A 
        add index FK_of0vrrmxfjckl3khul4hiukkw (REV), 
        add constraint FK_of0vrrmxfjckl3khul4hiukkw 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEAMSPACE_APP_A 
        add index FK_4j0u2y1shmyop97jo8av9sgyv (REV), 
        add constraint FK_4j0u2y1shmyop97jo8av9sgyv 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEAMSPACE_APP_PROPERTY_A 
        add index FK_neaevm02cgidd1ficvhda6ied (REV), 
        add constraint FK_neaevm02cgidd1ficvhda6ied 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEAMSPACE_MEMBER 
        add index FK_fy25hc5otirwwptajgifrbn2q (folk_id), 
        add constraint FK_fy25hc5otirwwptajgifrbn2q 
        foreign key (folk_id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_TEAMSPACE_MEMBER 
        add index FK_hidouigp2cvecq4okjdpmv9je (team_space_id), 
        add constraint FK_hidouigp2cvecq4okjdpmv9je 
        foreign key (team_space_id) 
        references RS_TEAMSPACE (ENTITY_ID);

    alter table RS_TEAMSPACE_MEMBER_A 
        add index FK_ehoe66nogdhk6u7w72uqd9jgo (REV), 
        add constraint FK_ehoe66nogdhk6u7w72uqd9jgo 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TEXT_PARAM_DEF 
        add index FK_6hn9ysftiusbc4sujut9ix47g (id), 
        add constraint FK_6hn9ysftiusbc4sujut9ix47g 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_TEXT_PARAM_DEF_A 
        add index FK_jng1qfcdv67hk2kmtl17c785k (id, REV), 
        add constraint FK_jng1qfcdv67hk2kmtl17c785k 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_TEXT_PARAM_INST 
        add index FK_bfec850w6d624tx9hjey0oabu (id), 
        add constraint FK_bfec850w6d624tx9hjey0oabu 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_TEXT_PARAM_INST_A 
        add index FK_pitf4s5vg5buwj3isdx267pi1 (id, REV), 
        add constraint FK_pitf4s5vg5buwj3isdx267pi1 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_TS_DISK_FOLDER 
        add index FK_d0d53xfagkqaatfjq8wqwqc6p (id), 
        add constraint FK_d0d53xfagkqaatfjq8wqwqc6p 
        foreign key (id) 
        references RS_TS_DISK_NODE (ENTITY_ID);

    alter table RS_TS_DISK_FOLDER_A 
        add index FK_87o62pam0d6n9r5ikdrgp9th9 (id, REV), 
        add constraint FK_87o62pam0d6n9r5ikdrgp9th9 
        foreign key (id, REV) 
        references RS_TS_DISK_NODE_A (ENTITY_ID, REV);

    alter table RS_TS_DISK_GENERAL_REFERENCE 
        add index FK_2uxjnc3iglxk5xcxosb5v5i77 (id), 
        add constraint FK_2uxjnc3iglxk5xcxosb5v5i77 
        foreign key (id) 
        references RS_TS_DISK_NODE (ENTITY_ID);

    alter table RS_TS_DISK_GENERAL_REFERENCE_A 
        add index FK_30kqu6voo5ov62rb19rpx4amv (id, REV), 
        add constraint FK_30kqu6voo5ov62rb19rpx4amv 
        foreign key (id, REV) 
        references RS_TS_DISK_NODE_A (ENTITY_ID, REV);

    alter table RS_TS_DISK_NODE 
        add index FK_151kppd1j2qmsfxu54ericrle (parent_id), 
        add constraint FK_151kppd1j2qmsfxu54ericrle 
        foreign key (parent_id) 
        references RS_TS_DISK_NODE (ENTITY_ID);

    alter table RS_TS_DISK_NODE_A 
        add index FK_e8wo1tixbiqq4n513pd753dxp (REV), 
        add constraint FK_e8wo1tixbiqq4n513pd753dxp 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_TS_DISK_REPORT_REFERENCE 
        add index FK_thq35pugu6l572ebbiy9h90es (report_id), 
        add constraint FK_thq35pugu6l572ebbiy9h90es 
        foreign key (report_id) 
        references RS_REPORT (id);

    alter table RS_TS_DISK_REPORT_REFERENCE 
        add index FK_ie3omwbvrf7mwlrx46cy10cwn (id), 
        add constraint FK_ie3omwbvrf7mwlrx46cy10cwn 
        foreign key (id) 
        references RS_TS_DISK_GENERAL_REFERENCE (id);

    alter table RS_TS_DISK_REPORT_REFERENCE_A 
        add index FK_oalceronepwgm3korcxwk4ky9 (id, REV), 
        add constraint FK_oalceronepwgm3korcxwk4ky9 
        foreign key (id, REV) 
        references RS_TS_DISK_GENERAL_REFERENCE_A (id, REV);

    alter table RS_TS_DISK_ROOT 
        add index FK_8pphff4tw4b3jjefjua9xxc42 (team_space_id), 
        add constraint FK_8pphff4tw4b3jjefjua9xxc42 
        foreign key (team_space_id) 
        references RS_TEAMSPACE (ENTITY_ID);

    alter table RS_TS_DISK_ROOT 
        add index FK_d8whd2jxj06cyvup2io6ipa9n (id), 
        add constraint FK_d8whd2jxj06cyvup2io6ipa9n 
        foreign key (id) 
        references RS_TS_DISK_NODE (ENTITY_ID);

    alter table RS_TS_DISK_ROOT_A 
        add index FK_j8ayfqaa4qtelmf7kv79das1v (id, REV), 
        add constraint FK_j8ayfqaa4qtelmf7kv79das1v 
        foreign key (id, REV) 
        references RS_TS_DISK_NODE_A (ENTITY_ID, REV);

    alter table RS_USER 
        add index FK_57e7c0013ntx24mt8xtncnact (id), 
        add constraint FK_57e7c0013ntx24mt8xtncnact 
        foreign key (id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_USERMANAGER_NODE 
        add index FK_rp5lo05enc72i78372idbylkl (parent_id), 
        add constraint FK_rp5lo05enc72i78372idbylkl 
        foreign key (parent_id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_USERMANAGER_NODE 
        add index FK_64wcr206hdn3q8vof7imdp01 (acl_id), 
        add constraint FK_64wcr206hdn3q8vof7imdp01 
        foreign key (acl_id) 
        references RS_HIERARCHICAL_ACL (id);

    alter table RS_USERMANAGER_NODE 
        add index FK_2cfi4lvw6j6quqcigy0g6eyo2 (owner_id), 
        add constraint FK_2cfi4lvw6j6quqcigy0g6eyo2 
        foreign key (owner_id) 
        references RS_USER (id);

    alter table RS_USERMANAGER_NODE_A 
        add index FK_cduy00jvfixjo7yv4vjpd71s0 (REV), 
        add constraint FK_cduy00jvfixjo7yv4vjpd71s0 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_USERVAR_DEF_A 
        add index FK_5om9kd630ey1aqg24r2ob8wx8 (REV), 
        add constraint FK_5om9kd630ey1aqg24r2ob8wx8 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_USERVAR_INST 
        add index FK_5buivmfd1h7p2do9ykevmww0f (definition_id), 
        add constraint FK_5buivmfd1h7p2do9ykevmww0f 
        foreign key (definition_id) 
        references RS_USERVAR_DEF (ENTITY_ID);

    alter table RS_USERVAR_INST 
        add index FK_qx4rr2aajx2apg7fiwam0pycb (folk_id), 
        add constraint FK_qx4rr2aajx2apg7fiwam0pycb 
        foreign key (folk_id) 
        references RS_USERMANAGER_NODE (ENTITY_ID);

    alter table RS_USERVAR_INST_A 
        add index FK_58p3y5kfgje9ghmkpgslbu5fb (REV), 
        add constraint FK_58p3y5kfgje9ghmkpgslbu5fb 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_USERVAR_PARAM_DEF 
        add index FK_m5hakblnowqepoavn6ik09ivs (user_variable_definition_id), 
        add constraint FK_m5hakblnowqepoavn6ik09ivs 
        foreign key (user_variable_definition_id) 
        references RS_USERVAR_DEF (ENTITY_ID);

    alter table RS_USERVAR_PARAM_DEF 
        add index FK_hte1xondnq461qgq4dvexbc4c (id), 
        add constraint FK_hte1xondnq461qgq4dvexbc4c 
        foreign key (id) 
        references RS_PARAMETER_DEFINITION (ENTITY_ID);

    alter table RS_USERVAR_PARAM_DEF_A 
        add index FK_nfhe0tvar1f3gkqt0kqwau20p (id, REV), 
        add constraint FK_nfhe0tvar1f3gkqt0kqwau20p 
        foreign key (id, REV) 
        references RS_PARAMETER_DEFINITION_A (ENTITY_ID, REV);

    alter table RS_USERVAR_PARAM_INST 
        add index FK_s7sp6ts9x0p7jn72tdsgoeqou (id), 
        add constraint FK_s7sp6ts9x0p7jn72tdsgoeqou 
        foreign key (id) 
        references RS_PARAMETER_INSTANCE (ENTITY_ID);

    alter table RS_USERVAR_PARAM_INST_A 
        add index FK_6tcovo5sdo5ww0b7bar5oinbl (id, REV), 
        add constraint FK_6tcovo5sdo5ww0b7bar5oinbl 
        foreign key (id, REV) 
        references RS_PARAMETER_INSTANCE_A (ENTITY_ID, REV);

    alter table RS_USER_2_PROPERTY 
        add constraint UK_jcm9jafge739ffhijr2lfk3hu unique (properties_id);

    alter table RS_USER_2_PROPERTY 
        add index FK_jcm9jafge739ffhijr2lfk3hu (properties_id), 
        add constraint FK_jcm9jafge739ffhijr2lfk3hu 
        foreign key (properties_id) 
        references RS_USER_PROPERTY (ENTITY_ID);

    alter table RS_USER_2_PROPERTY 
        add index FK_ekr2oll53tq8jr2hc0uw783y0 (user_id), 
        add constraint FK_ekr2oll53tq8jr2hc0uw783y0 
        foreign key (user_id) 
        references RS_USER (id);

    alter table RS_USER_2_PROPERTY_A 
        add index FK_67jjwwt94r6g04kx33k8fbk46 (REV), 
        add constraint FK_67jjwwt94r6g04kx33k8fbk46 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_USER_A 
        add index FK_lvy6oa9l11gx9gthkw732s4pb (id, REV), 
        add constraint FK_lvy6oa9l11gx9gthkw732s4pb 
        foreign key (id, REV) 
        references RS_USERMANAGER_NODE_A (ENTITY_ID, REV);

    alter table RS_USER_PROPERTY_A 
        add index FK_m1c6cve1due4tifp0as6j27pv (REV), 
        add constraint FK_m1c6cve1due4tifp0as6j27pv 
        foreign key (REV) 
        references RS_REVISION (ENTITY_ID);

    alter table RS_WEEKLY_CONFIG_2_DAYS 
        add index FK_dfj75myt4o7848o0dq64rudie (weekly_config_id), 
        add constraint FK_dfj75myt4o7848o0dq64rudie 
        foreign key (weekly_config_id) 
        references RS_DATE_TRIGGER_CONFIG (ENTITY_ID);

    create table RS_HIBERNATE_SEQUENCE ( next_val bigint ) CHARACTER SET utf8 COLLATE utf8_bin;
    insert into RS_HIBERNATE_SEQUENCE values ( 1 );

    create table RS_SCHEMAINFO (
        ENTITY_ID bigint not null auto_increment,
        KEY_FIELD varchar(128) not null,
        value longtext,
        primary key (ENTITY_ID)
    ) CHARACTER SET utf8 COLLATE utf8_bin;
    insert into RS_SCHEMAINFO(KEY_FIELD, value) VALUES('created', date_format(now(), '%Y-%m-%d %T'));
    insert into RS_SCHEMAINFO(KEY_FIELD, value) VALUES('version', 'RS3.4.0-6036');
    insert into RS_SCHEMAINFO(KEY_FIELD, value) VALUES('schemaversion', 'RS3.0-18');
