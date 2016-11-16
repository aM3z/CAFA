#' A Protein Sequence Function
#'
#' This function loads a FASTA file as a dataframe.
#' @param filepath path to FASTA file
#' @return data.frame dataframe with a seq_name and sequence column
#' @keywords fasta, data input
#' @export
#' @examples
#' fasta2dataframe("../go_sequences.fasta")

fasta2dataframe<-function(filepath){

	f <- Biostrings::readDNAStringSet(filepath)
	seq_name = names(f)
	sequence = paste(f)
	df <- data.frame(seq_name, sequence)
	return(df)
}
