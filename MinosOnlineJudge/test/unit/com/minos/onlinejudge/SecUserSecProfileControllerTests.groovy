package com.minos.onlinejudge



import org.junit.*
import grails.test.mixin.*

@TestFor(SecUserSecProfileController)
@Mock(SecUserSecProfile)
class SecUserSecProfileControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/secUserSecProfile/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.secUserSecProfileInstanceList.size() == 0
        assert model.secUserSecProfileInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.secUserSecProfileInstance != null
    }

    void testSave() {
        controller.save()

        assert model.secUserSecProfileInstance != null
        assert view == '/secUserSecProfile/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/secUserSecProfile/show/1'
        assert controller.flash.message != null
        assert SecUserSecProfile.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/secUserSecProfile/list'

        populateValidParams(params)
        def secUserSecProfile = new SecUserSecProfile(params)

        assert secUserSecProfile.save() != null

        params.id = secUserSecProfile.id

        def model = controller.show()

        assert model.secUserSecProfileInstance == secUserSecProfile
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/secUserSecProfile/list'

        populateValidParams(params)
        def secUserSecProfile = new SecUserSecProfile(params)

        assert secUserSecProfile.save() != null

        params.id = secUserSecProfile.id

        def model = controller.edit()

        assert model.secUserSecProfileInstance == secUserSecProfile
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/secUserSecProfile/list'

        response.reset()

        populateValidParams(params)
        def secUserSecProfile = new SecUserSecProfile(params)

        assert secUserSecProfile.save() != null

        // test invalid parameters in update
        params.id = secUserSecProfile.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/secUserSecProfile/edit"
        assert model.secUserSecProfileInstance != null

        secUserSecProfile.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/secUserSecProfile/show/$secUserSecProfile.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        secUserSecProfile.clearErrors()

        populateValidParams(params)
        params.id = secUserSecProfile.id
        params.version = -1
        controller.update()

        assert view == "/secUserSecProfile/edit"
        assert model.secUserSecProfileInstance != null
        assert model.secUserSecProfileInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/secUserSecProfile/list'

        response.reset()

        populateValidParams(params)
        def secUserSecProfile = new SecUserSecProfile(params)

        assert secUserSecProfile.save() != null
        assert SecUserSecProfile.count() == 1

        params.id = secUserSecProfile.id

        controller.delete()

        assert SecUserSecProfile.count() == 0
        assert SecUserSecProfile.get(secUserSecProfile.id) == null
        assert response.redirectedUrl == '/secUserSecProfile/list'
    }
}
