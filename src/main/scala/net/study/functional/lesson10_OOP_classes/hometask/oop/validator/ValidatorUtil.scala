package net.study.functional.lesson10_OOP_classes.hometask.oop.validator



import net.study.functional.lesson10_OOP_classes.hometask.oop.errors._
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.Experience

import java.util.Date
// Here you can declare all supplementary method for validation with style below(or use your own signature and return type
// if you want)
object ValidatorUtil {

  def validateStringEmptyParam(paramName: String, maybeStringEmpty: Option[String]): ValidationError = {
    maybeStringEmpty match {
      case Some(value) if value.isEmpty => ValidationError(Map(paramName -> EmptyStringError));
      case None => ValidationError(Map(paramName -> EmptyStringError));
    }
  }

  def validateDateEmptyParam(paramName: String, maybeDateEmpty: Option[Date]): ValidationError = {
    maybeDateEmpty match {
      case None => ValidationError(Map(paramName -> EmptyDateError));
    }
  }

  def validateListStringEmptyParam(paramName: String, maybeListStringEmpty: Option[List[String]]): ValidationError = {
    maybeListStringEmpty match {
      case Some(value) if value.isEmpty => ValidationError(Map(paramName -> EmptyListError));
    }
  }

  def validateListExperienceEmptyParam(paramName: String, maybeListExperienceEmpty: Option[List[Experience]]): ValidationError = {
    maybeListExperienceEmpty match {
      case Some(value) if value.isEmpty => ValidationError(Map(paramName -> EmptyListError));
    }
  }
}
