package com.asc.ts.gra.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import lombok.EqualsAndHashCode;

/**
 *
 * @author andre.cardoso
 * @param <E> Entity class
 * @data 17.08.2024
 */

@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class AbstractEntity<E extends AbstractEntity> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    public abstract void update(E obj);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return toString(this);
    }
    
    public String toString(Object object) {
        if (object == null) {
            return null;
        }

        Class<?> clazz = object.getClass();
        StringBuilder sb = new StringBuilder(clazz.getSimpleName()).append(" {");

//        while (clazz != null && !clazz.equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) {                    
                    try {
                        f.setAccessible(true);
                        sb.append(f.getName()).append(" = ").append(f.get(object)).append(",");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
//            if (!recursive) {
//                break;
//            }
            
//            clazz = clazz.getSuperclass();
            
//        }

        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.append("}").toString();
    }
}
