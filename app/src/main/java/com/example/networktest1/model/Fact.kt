package com.example.networktest1.model

data class Fact(
    val data: List<DataLocal?>?,
    val currentPage: Int?
) {

    companion object{
        fun fromFactDTO(factDTO: FactDTO): Fact {
            return Fact(
                data = factDTO.data?.map { DataLocal.fromDataDTO(it) },
                currentPage = factDTO.currentPage
            )
        }
    }


    data class DataLocal(
        val fact: String?,
        val length: Int?
    ) {
        companion object{
            fun fromDataDTO(dataRemote: FactDTO.DataDTO?): DataLocal {
                return DataLocal(
                    fact = dataRemote?.fact,
                    length = dataRemote?.length
                )
            }
        }
    }
}
