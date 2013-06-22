package com.minos.onlinejudge



import org.junit.*
import grails.test.mixin.*

@TestFor(SecUserController)
@Mock(SecUser)
class SecUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/secUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.secUserInstanceList.size() == 0
        assert model.secUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.secUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.secUserInstance != null
        assert view == '/secUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/secUser/show/1'
        assert controller.flash.message != null
        assert SecUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/secUser/list'

        populateValidParams(params)
        def secUser = new SecUser(params)

        assert secUser.save() != null

        params.id = secUser.id

        def model = controller.show()

        assert model.secUserInstance == secUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/secUser/list'

        populateValidParams(params)
        def secUser = new SecUser(params)

        assert secUser.save() != null

        params.id = secUser.id

        def model = controller.edit()

        assert model.secUserInstance == secUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/secUser/list'

        response.reset()

        populateValidParams(params)
        def secUser = new SecUser(params)

        assert secUser.save() != null

        // test invalid parameters in update
        params.id = secUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/secUser/edit"
        assert model.secUserInstance != null

        secUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/secUser/show/$secUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        secUser.clearErrors()

        populateValidParams(params)
        params.id = secUser.id
        params.version = -1
        controller.update()

        assert view == "/secUser/edit"
        assert model.secUserInstance != null
        assert model.secUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/secUser/list'

        response.reset()

        populateValidParams(params)
        def secUser = new SecUser(params)

        assert secUser.save() != null
        assert SecUser.count() == 1

        params.id = secUser.id

        controller.delete()

        assert SecUser.count() == 0
        assert SecUser.get(secUser.id) == null
        assert response.redirectedUrl == '/secUser/list'
    }
}
