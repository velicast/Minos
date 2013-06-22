package com.minos.onlinejudge.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(ClarificationController)
@Mock(Clarification)
class ClarificationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/clarification/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.clarificationInstanceList.size() == 0
        assert model.clarificationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.clarificationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.clarificationInstance != null
        assert view == '/clarification/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/clarification/show/1'
        assert controller.flash.message != null
        assert Clarification.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/clarification/list'

        populateValidParams(params)
        def clarification = new Clarification(params)

        assert clarification.save() != null

        params.id = clarification.id

        def model = controller.show()

        assert model.clarificationInstance == clarification
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/clarification/list'

        populateValidParams(params)
        def clarification = new Clarification(params)

        assert clarification.save() != null

        params.id = clarification.id

        def model = controller.edit()

        assert model.clarificationInstance == clarification
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/clarification/list'

        response.reset()

        populateValidParams(params)
        def clarification = new Clarification(params)

        assert clarification.save() != null

        // test invalid parameters in update
        params.id = clarification.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/clarification/edit"
        assert model.clarificationInstance != null

        clarification.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/clarification/show/$clarification.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        clarification.clearErrors()

        populateValidParams(params)
        params.id = clarification.id
        params.version = -1
        controller.update()

        assert view == "/clarification/edit"
        assert model.clarificationInstance != null
        assert model.clarificationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/clarification/list'

        response.reset()

        populateValidParams(params)
        def clarification = new Clarification(params)

        assert clarification.save() != null
        assert Clarification.count() == 1

        params.id = clarification.id

        controller.delete()

        assert Clarification.count() == 0
        assert Clarification.get(clarification.id) == null
        assert response.redirectedUrl == '/clarification/list'
    }
}
