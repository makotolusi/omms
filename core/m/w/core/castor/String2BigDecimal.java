package m.w.core.castor;

import java.math.BigDecimal;

import org.nutz.castor.Castor;
import org.nutz.castor.FailToCastObjectException;

public class String2BigDecimal extends Castor<String, BigDecimal> {

    @Override
    public BigDecimal cast(String src, Class<?> toType, String... args)
            throws FailToCastObjectException {
        if(null == src){
            return null;
        }
        return new BigDecimal((String)src);
    }

}
