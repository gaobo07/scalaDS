package queues


class Shares(var number: Int, val cost: Long)

class CapitalGains {
  private val Q : Queue[Shares] = 
    new scala.collection.mutable.Queue[Shares] with Queue[Shares]
  
  def buy(number: Int, cost: Long) {
    printf("Buying %d shares at KRW%d per share\n", number, cost)
    Q.enqueue(new Shares(number, cost))
  }
  
  def sell(total: Int, cost: Long) {
    printf("Selling %d shares at KRW%d per share\n", total, cost)
    var number = total
    while (number > 0) {
      val oldest = Q.head
      if (number < oldest.number) {
	printf("%d shares were bought for KRW%d so profit is KRW%d\n",
		number, oldest.cost, number * (cost - oldest.cost))
	oldest.number -= number
	number = 0
      } else {
	printf("%d shares were bought for KRW%d so profit is KRW%d\n",
	       oldest.number, oldest.cost,
	       oldest.number * (cost - oldest.cost))
	number -= oldest.number
	Q.dequeue()
      }
    }
  }
}

object CapitalGainsTest {

  def main(args: Array[String]) {
    val sp = new CapitalGains
    sp.buy(10, 20000)
    sp.buy(5, 21000)
    sp.buy(20, 19000)
    sp.sell(5, 23000)
    sp.sell(12, 22000)
    sp.buy(10, 21000)
    sp.sell(28, 22000)
  }
}

