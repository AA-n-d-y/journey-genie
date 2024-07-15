package com.genie.journey_genie.models;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Arrays;

public class CustomStringArrayType implements UserType<String[]> {
    @Override
    public Class<String[]> returnedClass() {
        return String[].class;
    }

    @Override
    public String[] nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        Array array = rs.getArray(position);
        return array != null ? (String[]) array.getArray() : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, String[] value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (st != null) {
            if (value != null) {
                Array array = session.getJdbcConnectionAccess().obtainConnection().createArrayOf("text", value);
                st.setArray(index, array);

            } else {
                st.setNull(index, Types.ARRAY);
            }
        }
    }

    @Override
    public String[] deepCopy(String[] value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public int hashCode(String[] strings) {
        return Arrays.hashCode(strings);
    }

    @Override
    public boolean equals(String[] x, String[] y) {
        return Arrays.equals(x, y);
    }

    @Override
    public String[] assemble(Serializable serializable, Object o) {
        return new String[0];
    }

    @Override
    public Serializable disassemble(String[] strings) {
        return this.deepCopy(strings);
    }

    @Override
    public String[] replace(String[] detached, String[] managed, Object owner) {
        return UserType.super.replace(detached, managed, owner);
    }

    @Override
    public int getSqlType() {
        return Types.ARRAY;
    }
}
