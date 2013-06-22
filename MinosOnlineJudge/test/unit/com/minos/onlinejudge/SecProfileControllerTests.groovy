package com.minos.onlinejudge



import org.junit.*
import grails.test.mixin.*

@TestFor(SecProfileController)
@Mock(SecProfile)
class SecProfileControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/secProfile/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.secProfileInstanceList.size() == 0
        assert model.secProfileInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.secProfileInstance != null
    }

    void testSave() {
        controller.save()

        assert model.secProfileInstance != null
        assert view == '/secProfile/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/secProfile/show/1'
        assert controller.flash.message != null
        assert SecProfile.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/secProfile/list'

        populateValidParams(params)
        def secProfile = new SecProfile(params)

        assert secProfile.save() != null

        params.id = secProfile.id

        def model = controller.show()

        assert model.secProfileInstance == secProfile
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/secProfile/list'

        populateValidParams(params)
        def secProfile = new SecProfile(params)

        assert secProfile.save() != null

        params.id = secProfile.id

        def model = controller.edit()

        assert model.secProfileInstance == secProfile
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/secProfile/list'

        response.reset()

        populateValidParams(params)
        def secProfile = new SecProfile(params)

        assert secProfile.save() != null

        // test invalid parameters in update
        params.id = secProfile.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/secProfile/edit"
        assert model.secProfileInstance != null

        secProfile.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/secProfile/show/$secProfile.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        secProfile.clearErrors()

        populateValidParams(params)
        params.id = secProfile.id
        params.version = -1
        controller.update()

        assert view == "/secProfile/edit"
        assert model.secProfileInstance != null
        assert model.secProfileInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/secProfile/list'

        response.reset()

        populateValidParams(params)
        def secProfile = new SecProfile(params)

        assert secProfile.save() != null
        assert SecProfile.count() == 1

        params.id = secProfile.id

        controller.delete()

        assert SecProfile.count() == 0
        assert SecProfile.get(secProfile.id) == null
        assert response.redirectedUrl == '/secProfile/list'
    }
}
