package main

import "fmt"

func produtor(c chan<- int) {
	for i := 1; i <= 5; i++ {
		c <- i
	}
	close(c)
}

func consumidor(c <-chan int) {
	for valor := range c {
		fmt.Println(valor)
	}
}

func main() {
	canal := make(chan int)

	go produtor(canal)
	consumidor(canal)
}
