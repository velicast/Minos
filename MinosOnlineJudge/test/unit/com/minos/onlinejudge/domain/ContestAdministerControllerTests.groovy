package com.minos.onlinejudge.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(ContestAdministerController)
@Mock(ContestAdminister)
class ContestAdministerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contestAdminister/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contestAdministerInstanceList.size() == 0
        assert model.contestAdministerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contestAdministerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contestAdministerInstance != null
        assert view == '/contestAdminister/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contestAdminister/show/1'
        assert controller.flash.message != null
        assert ContestAdminister.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contestAdminister/list'

        populateValidParams(params)
        def contestAdminister = new ContestAdminister(params)

        assert contestAdminister.save() != null

        params.id = contestAdminister.id

        def model = controller.show()

        assert model.contestAdministerInstance == contestAdminister
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contestAdminister/list'

        populateValidParams(params)
        def contestAdminister = new ContestAdminister(params)

        assert contestAdminister.save() != null

        params.id = contestAdminister.id

        def model = controller.edit()

        assert model.contestAdministerInstance == contestAdminister
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contestAdminister/list'

        response.reset()

        populateValidParams(params)
        def contestAdminister = new ContestAdminister(params)

        assert contestAdminister.save() != null

        // test invalid parameters in update
        params.id = contestAdminister.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contestAdminister/edit"
        assert model.contestAdministerInstance != null

        contestAdminister.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contestAdminister/show/$contestAdminister.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contestAdminister.clearErrors()

        populateValidParams(params)
        params.id = contestAdminister.id
        params.version = -1
        controller.update()

        assert view == "/contestAdminister/edit"
        assert model.contestAdministerInstance != null
        assert model.contestAdministerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contestAdminister/list'

        response.reset()

        populateValidParams(params)
        def contestAdminister = new ContestAdminister(params)

        assert contestAdminister.save() != null
        assert ContestAdminister.count() == 1

        params.id = contestAdminister.id

        controller.delete()

        assert ContestAdminister.count() == 0
        assert ContestAdminister.get(contestAdminister.id) == null
        assert response.redirectedUrl == '/contestAdminister/list'
    }
}
