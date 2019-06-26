package com.videorental.customer.infrastructure

import com.videorental.customer.command.CreateCustomerCommand
import com.videorental.db.config.LiquibaseDataSourceHolder
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class CustomerRepositoryImplInt extends Specification {

    @Shared CustomerRepositoryImpl repository

    def setupSpec() {
        repository = new CustomerRepositoryImpl(LiquibaseDataSourceHolder.getDSLContext())
    }

    def "save new customer in DB"() {
        given:
            def command = new CreateCustomerCommand("John", "Doe")
        when:
            def id = repository.save(command)
        then:
            id != null
            id.id() != null
    }

    def "get customer from DB"() {
        given:
            def id = repository.save(new CreateCustomerCommand("John", "Doe"))
        when:
            def customer = repository.get(id).get()
        then:
            customer != null
            customer.id == id
            customer.firstName == "John"
            customer.lastName == 'Doe'
    }
}
