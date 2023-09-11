package net.study.functional.lesson10_OOP_classes.hometask.oop.validator

import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.{Error, ValidationError}
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.{Experience, SignInRequest, SignUpRequest, WorkInfoRequest}




// here you can implement sub-traits for validation purpose


trait RequestValidator[R]{

  def validate(request: R): Either[Error, R]

}

object RequestValidator {

   val Name    = "name"
   val Surname = "surname"
   val Login   = "login"
   val pass    = "pass"
   val Msisdn  = "msisdn"
}

trait SignInValidator extends RequestValidator[SignInRequest]{
   def validate(request: SignInRequest): Either[Error, SignInRequest] = {
      val resultLoginError: ValidationError = ValidatorUtil.validateStringEmptyParam("SignInRequest.login",request.login)
      val resultPassError: ValidationError = ValidatorUtil.validateStringEmptyParam("SignInRequest.password",request.password)
      val allErrors = resultLoginError + resultPassError

      if (allErrors.errors.nonEmpty) Left(allErrors) else Right(request)
   }
}

/**
 * if present, all params must be absent
 *
 * @param organisation
 * @param position not empty
 * @param from     must be less or equal to
 * @param to       must be greater or equal from
 */
trait ExperienceValidation extends RequestValidator[Experience]{
   def validate(request: Experience): Either[Error, Experience] = {
      val resultPositionError: ValidationError = ValidatorUtil.validateStringEmptyParam("Experience.password", request.position)
      val resultFromError: ValidationError = ValidatorUtil.validateDateEmptyParam("Experience.login", request.from)
      val resultToError: ValidationError = ValidatorUtil.validateDateEmptyParam("Experience.password", request.to)
      val allErrors = resultPositionError + resultFromError + resultToError

      if (allErrors.errors.nonEmpty) Left(allErrors) else Right(request)
   }
}

/**
 * @param name    must be not empty, latin symbols only
 * @param surname must be not empty, latin symbols only
 * @param login   must be not empty, latin symbols and/or digits only  and unique across service
 * @param msisdn  must be not empty, only digits  symbols and has 9 or 12 symbols
 */
trait SignUpValidator extends RequestValidator[SignUpRequest]{
   def validate(request: SignUpRequest): Either[Error, SignUpRequest] = {
      val resultNameError: ValidationError = ValidatorUtil.validateStringEmptyParam("SignUpRequest.name", request.name)
      val resultSurnameError: ValidationError = ValidatorUtil.validateStringEmptyParam("SignUpRequest.surname", request.surname)
      val resultLoginError: ValidationError = ValidatorUtil.validateStringEmptyParam("SignUpRequest.login", request.login)
      val resultMsisdnError: ValidationError = ValidatorUtil.validateStringEmptyParam("SignUpRequest.msisdn", request.msisdn)
      val allErrors = resultNameError + resultSurnameError + resultLoginError + resultMsisdnError

      if (allErrors.errors.nonEmpty) Left(allErrors) else Right(request)
   }
}

/**
 * @param workExperience can be optional, but can't be empty, and if present any Experience object, its fields must be present!
 * @param certificates   can pe optional, but can't be empty
 *                       at least work experience or certificates must be present
 * */
trait WorkInfoValidation extends RequestValidator[WorkInfoRequest]{
   def validate(request: WorkInfoRequest): Either[Error, WorkInfoRequest] = {
      val resultWorkExperienceError: ValidationError = ValidatorUtil.validateListExperienceEmptyParam("WorkInfoRequest.workExperience", request.workExperience)
      val resultCertificatesError: ValidationError = ValidatorUtil.validateListStringEmptyParam("WorkInfoRequest.certificates", request.certificates)
      val allErrors = resultWorkExperienceError + resultCertificatesError

      if (allErrors.errors.nonEmpty) Left(allErrors) else Right(request)
   }
}


