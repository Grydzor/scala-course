package net.study.functional.lesson3_case_classes_and_monade

import net.study.functional.lesson3_case_classes_and_monade.HomeTask._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, OptionValues, WordSpec}

@RunWith(classOf[JUnitRunner])
class HomeTaskTest extends WordSpec
  // with MockFactory
  with OptionValues
  with Matchers {

  "A tax" when {
    "correct" should {
      "be the same" in{
        correctTax(Some(200), 1000) shouldEqual Some(200)
      }
    }
    "empty and the sum less then 100" should {
      "free" in {
        correctTax(None, 99) shouldEqual Some(0)
      }
    }
    "empty" should {
      "20%" in {
        correctTax(None, 1000) shouldEqual Some(200)
      }
    }
  }

  "A payment" when {
    "correct" should {
      "be the same" in {
        correctPayment(1, Some(100)) shouldEqual Some(100)
      }
    }
    "empty" should {
      "get from PaymentCenter" in {
        correctPayment(9, None) shouldEqual Some(900)
      }
      "skipped if PaymentCanter haven`t info" in {
        correctPayment(2, None) shouldEqual None
      }
    }
  }

  "A description" when {
    "correct" should {
      "be the same" in {
        correctDesc(Some("payment for Iphone 15")) shouldEqual Some("payment for Iphone 15")
      }
    }
    "empty" should {
      "replaced to technical" in {
        correctDesc(None) shouldEqual Some("technical")
      }
    }
  }

  "A payment DTO" when {
    "correct" should {
      "be converted to the info" in {
        val dto = PaymentInfoDto(6, Some("customerE"), Some(600), Some(120), Some("payment for Oculus quest 2"))
        val info = Some(PaymentInfo(6, 600, 120, "payment for Oculus quest 2"))
        correctPaymentInfo(dto) shouldEqual info
      }
    }
  }

}
