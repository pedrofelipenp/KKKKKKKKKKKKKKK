package main

import "fmt"

func produtor(c chan<- int) {
	for i := 1; i <= 5; i++ {
		c <- i
	}
}

func main() {
	canal := make(chan int)

	go produtor(canal)

	for i := 1; i <= 5; i++ {
		fmt.Println(<-canal)
	}
}