package com.getontop.candidate.kariaki.securenote.exceptions

class JsonFileFormatException():Exception(){
    override val message: String
        get() = "Json file format exception"
}