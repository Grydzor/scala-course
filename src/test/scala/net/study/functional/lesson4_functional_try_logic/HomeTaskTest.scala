package net.study.functional.lesson4_functional_try_logic

import net.study.functional.lesson4_functional_try_logic.HomeTask._
import org.junit.runner.RunWith
import org.scalatest.{Matchers, OptionValues, WordSpec}
import org.scalatest.junit.JUnitRunner

import scala.util.Try

@RunWith(classOf[JUnitRunner])
class HomeTaskTest extends WordSpec
  // with MockFactory
  with OptionValues
  with Matchers {

  val fileSource = "src/test/resources/lesson6/externalSourceFile.txt"
  val subscribersInfo: Seq[SubscriberInfo] = Seq(
    SubscriberInfo("0673052785", 1, isActive = false),
    SubscriberInfo("0673052786", 0, isActive = false),
    SubscriberInfo("0673052787", 1, isActive = false),
    SubscriberInfo("0673052788", 1, isActive = false),
    SubscriberInfo("0673052789", 0, isActive = false)
  )

  "A file" when {
    "risky" should {
      "throw a NetworkException" in {
        assertThrows[NetworkException] {
          val bufferedSource = getFile(isRisky = true, fileSource)
        }
      }
    }
    "safe" should {
      "have no exception" in {
        val bufferedSource = getFile(isRisky = false, fileSource)
        assert(bufferedSource.nonEmpty)
      }
    }
  }

  "A subscriber info" when {
    "correct" should {
      "parsed without errors" in {
        val parsed = SubscriberInfo("0673052785", 1, isActive = false)
        toSubscriber("0673052785;1") shouldEqual Some(parsed)
      }
    }
    "invalid" should {
      "throw RuntimeException" in {
        assertThrows[RuntimeException] {
          val parsed = toSubscriber("06730527851")
        }
      }
    }
  }

  "Subscribers" when {
    "parsed from file" should {
      "be equal with parsed data" in {
        tryGetSubscribers(isRisky = false, fileSource).get shouldEqual subscribersInfo
      }
    }
    "can not be parsed" should {
      "throw " in {
        assertThrows[NetworkException] {
          val parsed = tryGetSubscribers(isRisky = true, fileSource).get
        }
      }
    }
  }


}
