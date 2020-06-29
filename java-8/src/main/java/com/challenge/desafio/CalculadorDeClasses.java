package com.challenge.desafio;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.*;

public class CalculadorDeClasses implements Calculavel {

  @Override
  public BigDecimal somar(Object classe) {
    BigDecimal sum = BigDecimal.ZERO;
    for (Field field : classe.getClass().getDeclaredFields()) {
      if (isBigDecimal(field)) {
        for(Annotation annotation:field.getAnnotations())
        {
          
          if(isSumAnnotation(annotation)){
            try {
              field.setAccessible(true);
              sum = sum.add((BigDecimal) field.get(classe));
              break;
            } catch (IllegalArgumentException | IllegalAccessException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
        
      }else{
        return new BigDecimal(0);
      }
    }
    return sum;
  }

  public BigDecimal subtrair(Object classe) {
    BigDecimal sub = new BigDecimal(0);
    for (Field field : classe.getClass().getDeclaredFields()) {
      if (isBigDecimal(field)) {
        for(Annotation annotation:field.getAnnotations())
        {
          if(isSubAnnotation(annotation)){
            try {
              field.setAccessible(true);
              sub = sub.add((BigDecimal) field.get(classe));
              break;
            } catch (IllegalArgumentException | IllegalAccessException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
      }else{
        return new BigDecimal(0);
      }
    }
    return sub;
  }

  public BigDecimal totalizar(Object classe){
    BigDecimal sum = somar(classe);
    BigDecimal sub = subtrair(classe);
    if(sum.compareTo(new BigDecimal(0))==0 || sub.compareTo(new BigDecimal(0))==0){
      return new BigDecimal(0);
    }
    return sum.subtract(sub);
  }

  public Boolean isBigDecimal(Field field){
    return field.getType().getTypeName().equals("java.math.BigDecimal");
  }

  public Boolean isSumAnnotation (Annotation annotation){
    
    return annotation instanceof Somar;
  }

  public Boolean isSubAnnotation (Annotation annotation){
    return annotation instanceof Subtrair;
  }
}