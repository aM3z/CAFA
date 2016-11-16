#' Converts sequences to numeric vector of primes products
#'
#' @param seq protein sequence
#' @return numeric vector of prime products
#' @export
#' @examples
#' to_vector("ATAGACGATACGATACCCCGAGGGTAGGTA")

to_vector<-function(seq) {

	codes<-vector(mode="list", length=28)
	names(codes)<-c("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","Y","Z","X","*","-")
	codes[[1]]<-2
	codes[[2]]<-3
	codes[[3]]<-5
	codes[[4]]<-7
	codes[[5]]<-11
	codes[[6]]<-13
	codes[[7]]<-17
	codes[[8]]<-19
	codes[[9]]<-23
	codes[[10]]<-29
	codes[[11]]<-31
	codes[[12]]<-37
	codes[[13]]<-41
	codes[[14]]<-43
	codes[[15]]<-47
	codes[[16]]<-53
	codes[[17]]<-59
	codes[[18]]<-61
	codes[[19]]<-67
	codes[[20]]<-71
	codes[[21]]<-73
	codes[[22]]<-79
	codes[[23]]<-83
	codes[[24]]<-89
	codes[[25]]<-97
	codes[[26]]<-101
	codes[[27]]<-103
	codes[[28]]<-107

	if(nchar(seq) %% 2 == 0) {
		res<-numeric(nchar(seq) / 2)
	} else {
		res<-numeric(nchar(seq) / 2 + 1)
	}

	for(i in seq(from=1,to=nchar(seq),by=2)) {

		left<-as.numeric(codes[substr(seq,i,i)])
		if(i+1<=nchar(seq)) {
			right<-as.numeric(codes[substr(seq,i+1,i+1)])
		} else {
			right<-1
		}
		res[i/2 + 1/2]<-left*right**2
	}

	return(res)
}
