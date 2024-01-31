// // @main
// // def mainProg: Unit = {
// //   def pairElements(list1: List[Int], list2: List[Int]): List[(Int, Int)] = {
// //     @annotation.tailrec
// //     def helper(l1: List[Int], l2: List[Int], acc: List[(Int, Int)]): List[(Int, Int)] = {
// //       l1 match {
// //         case Nil => acc
// //         case head1 :: tail1 =>
// //           @annotation.tailrec
// //           def innerHelper(l2: List[Int], acc: List[(Int, Int)]): List[(Int, Int)] = {
// //             l2 match {
// //               case Nil => acc
// //               case head2 :: tail2 => innerHelper(tail2, (head1, head2) :: acc)
// //             }
// //           }
// //           helper(tail1, l2, innerHelper(l2, acc))
// //       }
// //     }
// //     helper(list1, list2, List())
// //   }
// //   println(pairElements(List(1, 2, 3), List(4, 5, 6)))
// //   return ()
// // }

// @main
// def mainProg: Unit = {
//  def dopasuj[A,B](l1: List[A], l2: List[B]): Set[(A,B)] = {
//   def helper2(el: A, l: List[B], acc: List[(A,B)]): List[(A,B)] = {
//    l match {
//     case List() => acc
//     case lHead :: lTail => helper(el, lTail, (el, head2)::acc)
//    }
//   }
//   def helper(l1: List[A], acc: List[(A,B)]): Set[(A,B)] = {
//    l1 match {
//     case List() => acc.toSet
//     case lHead :: lTail => helper(lTail, helper(lHead, l2, acc))
//    }
//   }
//   helper2(l1, Nil)
//  }
//  println(dopasuj(List(1, 2, 3), List(4, 5, 6)))
//  return ()
// }
// Funkcja countOccurrences jest funkcją rekurencyjną, która zlicza liczbę wystąpień danego numeru w liście. Przyjmuje listę, numer do zliczenia i akumulator (z wartością domyślną 0), który przechowuje dotychczasową liczbę wystąpień. Jeśli lista jest pusta, zwraca wartość akumulatora. W przeciwnym razie, jeśli głowa listy jest równa szukanemu numerowi, zwiększa akumulator o 1 i wywołuje się rekurencyjnie dla reszty listy. Jeśli głowa listy nie jest równa szukanemu numerowi, wywołuje się rekurencyjnie dla reszty listy bez zwiększania akumulatora.

// Funkcja check jest również funkcją rekurencyjną, która sprawdza, czy liczba wystąpień każdego numeru w liście jest równa oczekiwanej liczbie wystąpień. Przyjmuje listę par i akumulator (z wartością domyślną true), który przechowuje wynik dotychczasowych sprawdzeń. Jeśli lista par jest pusta, zwraca wartość akumulatora. W przeciwnym razie, jeśli liczba wystąpień numeru w liście (obliczona za pomocą countOccurrences) nie jest równa oczekiwanej liczbie wystąpień, wywołuje się rekurencyjnie dla reszty listy z akumulatorem ustawionym na false. Jeśli liczba wystąpień jest równa oczekiwanej liczbie wystąpień, wywołuje się rekurencyjnie dla reszty listy bez zmiany akumulatora.

// Na końcu, funkcja checkIfContains wywołuje funkcję check dla listy par uzyskanej z przekształcenia zbioru par na listę.

// W funkcji mainProg, funkcja checkIfContains jest wywoływana trzy razy z różnymi argumentami, a jej wyniki są wyświetlane na konsoli.
