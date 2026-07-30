package main

import (
	"fmt"
	"math/rand"
	"time"
)

func sensor(nome string, canal chan<- int, terminou chan<- bool) {
	for i := 0; i < 100; i++ {
		temperatura := rand.Intn(61)

		fmt.Println(nome, "mediu:", temperatura)

		canal <- temperatura

		time.Sleep(50 * time.Millisecond)
	}

	terminou <- true
}

func consumidor(canal <-chan int) {
	quantidade := 0
	soma := 0
	maior := 0
	menor := 60

	for temperatura := range canal {
		quantidade++
		soma += temperatura

		if temperatura > maior {
			maior = temperatura
		}

		if temperatura < menor {
			menor = temperatura
		}
	}

	media := float64(soma) / float64(quantidade)

	fmt.Println("\nResultados:")
	fmt.Println("Quantidade:", quantidade)
	fmt.Printf("Média: %.2f\n", media)
	fmt.Println("Maior temperatura:", maior)
	fmt.Println("Menor temperatura:", menor)
}

func main() {
	canal := make(chan int)
	terminou := make(chan bool)

	go sensor("Sensor A", canal, terminou)
	go sensor("Sensor B", canal, terminou)

	go func() {
		<-terminou
		<-terminou
		close(canal)
	}()

	consumidor(canal)
}