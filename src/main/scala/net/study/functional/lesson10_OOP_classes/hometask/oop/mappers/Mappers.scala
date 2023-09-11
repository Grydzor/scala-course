package net.study.functional.lesson10_OOP_classes.hometask.oop.mappers

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest
import net.study.functional.lesson10_OOP_classes.hometask.oop.services.HashService

// here you can assign your implicit mapper function  implement this trait with your logic
trait Mappers {

  // implement this
  implicit val signUpRequestMapper: SignUpRequest => SignUpDto = (r) => {
    val optDTO: Option[SignUpDto] = for {
      name <- r.name
      surname <- r.surname
      login <- r.login
      pass <- r.pass
      msisdn <-  r.msisdn
    } yield {
      SignUpDto(name, surname, login, hash(pass), msisdn)
    }

    optDTO match {
      case Some(value) => value
    }
  }

  private def hash(password: String): String = {
    val hashService = new HashService
    hashService.hash(password)
  }


}