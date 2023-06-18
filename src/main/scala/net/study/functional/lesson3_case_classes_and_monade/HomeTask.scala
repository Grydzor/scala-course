package net.study.functional.lesson3_case_classes_and_monade

object HomeTask extends App {

  // if sum not submitted, precise in payment service. In case not found remove from final report.
  // if tax sum is not assigned calculate it(20%) (minimal tax free sum for calculation = 100 or reversal payments are tax free)
  // if desc is empty default value will be "technical"
  // dublicates must be removed
  // get method is not allowed, use for comprehension in main calculation method
  // output must be type of Seq[PaymentInfo] !!!!!!!!!

  final case class PaymentInfoDto(paymentId: Int, customer: Option[String], sum: Option[Long], tax: Option[Long], desc: Option[String])

  final case class PaymentInfo(paymentId: Int, sum: Long, tax: Long, desc: String)

  object PaymentCenter {

    def getPaymentSum(id: Int): Option[Long] = Option(id) filter (_ > 2) map (_ * 100)
  }

  val payments = Seq(
    PaymentInfoDto(1, Some("customerA"), Some(1500), None, Some("payment for Iphone 15")),
    PaymentInfoDto(2, Some("customerH"), None, None, Some("technical payment")),
    PaymentInfoDto(3, Some("customerB"), Some(99), None, Some("payment for headphone")),
    PaymentInfoDto(4, Some("customerC"), Some(1000), Some(200), None),
    PaymentInfoDto(5, Some("customerD"), Some(2500), None, None),
    PaymentInfoDto(6, Some("customerE"), Some(600), Some(120), Some("payment for Oculus quest 2")),
    PaymentInfoDto(7, Some("customerF"), Some(1500), None, Some("payment for Iphone 15")),
    PaymentInfoDto(8, Some("customerG"), Some(-400), None, Some("roll back transaction")),
    PaymentInfoDto(9, Some("customerH"), None, None, Some("some payment")),
    PaymentInfoDto(4, Some("customerC"), Some(1000), Some(200), None)
  )

  val filteredDTO = payments.distinct
    .map(dto => dto.sum match {
      case None => dto.copy(sum = PaymentCenter.getPaymentSum(dto.paymentId))
      case _ => dto
    })
    .withFilter(dto => dto.sum.nonEmpty)

  var outputList: Seq[PaymentInfo] = for (dto <- filteredDTO) yield{

    val sum: Long = dto.sum match {
      case Some(a) => a
    }

    val tax: Long  = dto.tax match {
      case None => if (sum < 100) 0 else (sum * 0.2).longValue()
      case Some(a) => a
    }

    val desc: String = dto.desc match {
      case Some(a) => a
      case None => "technical"
    }

    val payment = PaymentInfo(paymentId = dto.paymentId, sum = sum, tax = tax, desc = desc)

    payment
  }

  outputList.foreach(println(_))

}