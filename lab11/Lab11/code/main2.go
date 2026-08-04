package main

import (
	"fmt"
	"time"
)

func produtor(c chan<- int) {
	for i := 1; i <= 20; i++ {
		fmt.Println("Enviando", i)
		c <- i
	}
	close(c)
}

func consumidor(c <-chan int) {
	for valor := range c {
		time.Sleep(100 * time.Millisecond)
		fmt.Println("Recebido", valor)
	}
}

func testarBuffer(tamanho int) {
	fmt.Println("\nBuffer:", tamanho)
	canal := make(chan int, tamanho)
	inicio := time.Now()

	go produtor(canal)
	consumidor(canal)

	fmt.Println("Tempo aproximado:", time.Since(inicio))
}

func main() {
	tamanhos := []int{0, 1, 5, 10, 20}

	for _, tamanho := range tamanhos {
		testarBuffer(tamanho)
	}
}
