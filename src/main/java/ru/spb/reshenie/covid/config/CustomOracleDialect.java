package ru.spb.reshenie.covid.config;

import org.hibernate.dialect.Oracle12cDialect;

/**
 * Created by vkondratiev on 12.11.2021
 */

@SuppressWarnings("unused")
public class CustomOracleDialect extends Oracle12cDialect {

    @Override
    public String getQuerySequencesString() {
        return "select SEQUENCE_OWNER, SEQUENCE_NAME, greatest(MIN_VALUE,         -9223372036854775807) MIN_VALUE,\n"+
                "Least(MAX_VALUE, 9223372036854775808) MAX_VALUE, INCREMENT_BY,     CYCLE_FLAG, ORDER_FLAG, CACHE_SIZE,\n"+
                "Least(greatest(LAST_NUMBER, -9223372036854775807), 9223372036854775808) LAST_NUMBER,\n"+
                "'' as PARTITION_COUNT,'' as  SESSION_FLAG,'' as  KEEP_VALUE\n"+
                "from all_sequences";
    }
}
