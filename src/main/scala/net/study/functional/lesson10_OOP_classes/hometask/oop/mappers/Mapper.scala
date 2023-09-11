package net.study.functional.lesson10_OOP_classes.hometask.oop.mappers

import net.study.functional.lesson10_OOP_classes.hometask.oop.dto.SignUpDto
import net.study.functional.lesson10_OOP_classes.hometask.oop.errors.Error
import net.study.functional.lesson10_OOP_classes.hometask.oop.request.SignUpRequest

private[oop] trait Mapper[R, DTO] {
  def map(request: R)(implicit defaultMapper: R => DTO): Either[Error, DTO]
}

trait SignUpMapper extends Mapper[SignUpRequest, SignUpDto]{
  def map(request: SignUpRequest)(implicit defaultMapper: SignUpRequest => SignUpDto): Either[Error, SignUpDto] = ???
}


