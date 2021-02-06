package by.andrus.askit.model.types;

import by.andrus.askit.model.enums.Roles;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgreSQLEnumType extends EnumType<Roles> {
    private static final long serialVersionUID = 4179731255786987018L;

    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        st.setObject(index, value != null ? ((Enum) value).name() : null, Types.OTHER);
    }
}